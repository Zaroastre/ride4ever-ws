package io.nirahtech.ride4ever.microservices.recovery;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.nirahtech.ride4ever.core.environment.Biker;
import io.nirahtech.ride4ever.core.web.exceptions.NotYetImplementedException;
import io.nirahtech.ride4ever.microservices.registration.RegistrationService;

final class RecoveryService implements RecoveryApi {
    
    private static final Map<UUID, GuardianThread> RECOVERIES = new HashMap<>();

    private RegistrationService service = new RegistrationService();


    @Override
    public Recovery findAccountByEmail(String email) throws RuntimeException {
        Biker bikerAccount = this.service.find(email);
        if (bikerAccount != null) {
            final UUID token = UUID.randomUUID();
            final Recovery recovery = new Recovery();
            recovery.setIdentity(email);
            recovery.setToken(token.toString());
            final GuardianThread guardian = new GuardianThread(10, ChronoUnit.MINUTES, recovery);
            RECOVERIES.put(token, guardian);
            guardian.start();
            return recovery;
        }
        return null;
    }

    private final int randomInt(final int min, final int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    private final String generateCode() {
        final int part1 = randomInt(0, 999);
        final int part2 = randomInt(0, 999);
        return String.format("R4E-%s-%s", ("000" + String.valueOf(part1)).substring(String.valueOf(part1).length()), ("000" + String.valueOf(part2)).substring(String.valueOf(part2).length()));
    }

    @Override
    public Recovery sendCodeByEmailTo(Recovery recovery) throws RuntimeException {
        if (recovery != null) {
            if (recovery.getIdentity() != null && recovery.getToken() != null) {
                if (RECOVERIES.containsKey(UUID.fromString(recovery.getToken()))) {
                    final UUID token = UUID.randomUUID();
                    final String code = this.generateCode();
                    GuardianThread guardian = RECOVERIES.get(UUID.fromString(recovery.getToken()));
                    guardian.setCode(code);
                    RECOVERIES.remove(UUID.fromString(guardian.getRecovery().getIdentity()));
                    guardian.getRecovery().setToken(token.toString());
                    RECOVERIES.put(UUID.fromString(guardian.getRecovery().getIdentity()), guardian);
                    return guardian.getRecovery();
                }
            }
        }
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Recovery sendCodeBySmsTo(Recovery recovery) throws RuntimeException {
        throw new NotYetImplementedException();
    }

    @Override
    public Recovery verifyCode(Recovery recovery) throws RuntimeException {
        if (recovery != null) {
            if (recovery.getIdentity() != null && recovery.getToken() != null && recovery.getCode() != null) {
                if (RECOVERIES.containsKey(UUID.fromString(recovery.getToken()))) {
                    final UUID token = UUID.randomUUID();
                    GuardianThread guardian = RECOVERIES.get(UUID.fromString(recovery.getToken()));
                    if (guardian.getCode().equals(recovery.getCode())) {
                        RECOVERIES.remove(UUID.fromString(guardian.getRecovery().getIdentity()));
                        guardian.getRecovery().setToken(token.toString());
                        RECOVERIES.put(UUID.fromString(guardian.getRecovery().getIdentity()), guardian);
                        return guardian.getRecovery();
                    }
                }
            }
        }
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Recovery changePassword(Recovery recovery) throws RuntimeException {
        if (recovery != null) {
            if (recovery.getIdentity() != null && recovery.getToken() != null && recovery.getCode() != null && recovery.getPassword() != null) {
                if (RECOVERIES.containsKey(UUID.fromString(recovery.getToken()))) {
                    final UUID token = UUID.randomUUID();
                    GuardianThread guardian = RECOVERIES.get(UUID.fromString(recovery.getToken()));
                    RECOVERIES.remove(UUID.fromString(guardian.getRecovery().getIdentity()));
                    guardian.getRecovery().setToken(token.toString());
                    // TODO Change password
                    guardian.terminate();
                }
            }
        }
        // TODO Auto-generated method stub
        return null;
    }

}
