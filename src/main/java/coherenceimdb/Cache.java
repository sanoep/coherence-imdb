package coherenceimdb;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.InvocableMap;
import com.tangosol.util.processor.ExtractorProcessor;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: shanny
 * Date: 15/06/13
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class Cache {

    private static NamedCache directorCache = CacheFactory.getCache("director");
    private static NamedCache ratingsCache = CacheFactory.getCache("rating");

    public static void init() {
        CacheFactory.ensureCluster();
    }

    public static void add(DirectorMovieList dm) {
        directorCache.put(dm.getDirector(), dm.getMovies());
    }

    public static void add(MovieRating mr) {
        ratingsCache.put(mr.getMovie(), mr.getRating());
    }

    public static void count() {

        Map map = directorCache.invokeAll((Filter) null, new ExtractorProcessor() {
            public Object process(InvocableMap.Entry entry) {
                int count = 0;
                List<String> movies = (List<String>) entry.getValue();
                if (movies == null) {
                    count = 0;
                } else {
                    count = movies.size();
                }
                return count;
            }
        });

        System.out.println("map result # entries: " + map.size());
        //System.out.println("map " + map);
    }

    public static void shutdown() {
        CacheFactory.shutdown();
    }

}
