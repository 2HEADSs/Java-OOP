package climbers.models.climbing;

import climbers.models.climber.Climber;
import climbers.models.mountain.Mountain;

import java.util.Collection;

public class ClimbingImpl implements Climbing {
    @Override
    public void conqueringPeaks(Mountain mountain, Collection<Climber> climbers) {
        Collection<String> peaks = mountain.getPeaksList();
        for (Climber climber : climbers) {
            while (climber.canClimb() && peaks.iterator().hasNext()) {
                String currentPeak = peaks.iterator().next();
                climber.climb();
                climber.getRoster().getPeaks().add(currentPeak);
                peaks.remove(currentPeak);
            }
        }
    }
}
