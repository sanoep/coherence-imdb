package coherenceimdb;

/**
 * Created with IntelliJ IDEA.
 * User: shanny
 * Date: 15/06/13
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
public interface IMDBParser {
    public void parseNextLine(String line, int index);
}