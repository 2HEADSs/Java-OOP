package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class GangNeighbourhood implements Neighbourhood {

    @Override
    public void action(Player tommy, Collection<Player> civilPlayers) {
        Repository<Gun> tommyGunRepo = tommy.getGunRepository();
        Deque<Gun> tommyGuns = new ArrayDeque<>(tommyGunRepo.getModels());
        Deque<Player> players = new ArrayDeque<>(civilPlayers);

        Player player = players.poll();
        Gun gun = tommyGuns.poll();
        while (player != null && gun != null) {
            while (gun.canFire() && player.isAlive()) {
                int shot = gun.fire();
                player.takeLifePoints(shot);
            }

            if (gun.canFire()) {
                player = players.poll();
            } else {
                gun = tommyGuns.poll();
            }

        }
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                Repository<Gun> civilPlayerGunRepo = civilPlayer.getGunRepository();
                Deque<Gun> civilPlayerGuns = new ArrayDeque<>(civilPlayerGunRepo.getModels());

                Gun civilPlayerGun = civilPlayerGuns.poll();
                while (civilPlayerGun != null) {
                    while (civilPlayerGun.canFire() && tommy.isAlive()) {
                        int shot = civilPlayerGun.fire();
                        tommy.takeLifePoints(shot);
                    }
                    if (!tommy.isAlive()) {
                        return;
                    }
                    civilPlayerGun = civilPlayerGuns.poll();
                }
            }
        }

    }
}
