  /* Assignment #5
   * Steven Stevenson
   * CSE 143 AF
   * TA: Kimberly Todd
   * 
   * GrammarSolver
   * Produces a randomly generated list of strings 
   * with nonterminals and terminals
   * ===============Public Methods========================
   * GrammarSolver(List<String> grammar)
   * grammarContains(String symbol)
   * getSymbols()
   * generate(String symbol, int times);
   * =====================================================
   */   

  import java.util.*;
  
  public class GrammarSolver {
    public SortedMap<String, String> theMap;
    
    //============================Constructors
    /* pre  : only unique nonterminals (no repetitions)
     *      : grammar cannot be null or empty
     *      : @throw new IllegalArgumentException if above preconditions not met
     * post : creates a SortedMap with nonterminals as 
     *      : keys and terminals as values
     */
    public GrammarSolver(List<String> grammar) {
      if ( grammar == null || grammar.size() == 0) {
        throw new IllegalArgumentException();
      }
      theMap = new TreeMap<String, String>();
      for (int i = 0; i < grammar.size(); i++) {
        StringTokenizer tok = new StringTokenizer(grammar.get(i),":");
        String nonterminal = tok.nextToken();
        if (grammarContains(nonterminal)) {
          throw new IllegalArgumentException();
        } else {
          String value = tok.nextToken();
          theMap.put( nonterminal, value );
        }
      }
    }
    
    //============================Accessors / Getters
    /* post : @return true if @param symbol is key (nonterminal)
     *      : @return false if @param symbol is value (terminal) or if symbol is null
     */
    public boolean grammarContains(String symbol) {
      return (symbol == null ) ? false : theMap.containsKey(symbol);
    }
    
    /* post : @return a list of keys (nonterminals)
     *      : toString description, list enclosed by brackets
     *      : and seperated my commas
     */
    public String getSymbols() {
      return theMap.keySet().toString();
    }
    
    //===========================Behaviours
    /* pre  : @param symbol must be nonterminal
     *      : @param times must be a nonnegative number
     *      : @throw IllegalArgumentException if preconditions not met
     * post : stores an amount (times) of randomly generated strings produced
     *      : by a private method.
     *      : @return an array of amount @param times of randomly generated strings
     */
    public String[] generate(String symbol, int times) {
      if ( symbol == null || !grammarContains(symbol) || times < 0) {
        throw new IllegalArgumentException();
      }
      String[] array = new String[times];
      for (int i = 0; i < times; i++) {
        array[i] = generate(symbol).trim();
      }
      return array;
    }
    
    //============================Private methods
    /* post : recursively creates a randomly generated string
     *      : that follows a structure of nonterminals and terminals. 
     *      : @return (final return) a randomly generated string
     *      : description: each terminal is seperated by exactly one space
     */
    private String generate(String symbol) {
      String theString = "";
      String[] values = theMap.get(symbol).split("[|]+");
      Random r = new Random();
      values = values[r.nextInt(values.length)].split("[ \t]+");
      for (int i = 0; i < values.length; i++) {
        if (grammarContains(values[i])) {
          theString += generate(values[i]);
        } else if ( i < values.length && values[i] != " ") {
          theString += values[i] + " ";
        } else if (values[i] != " ") {
          return theString += values[i] + " ";
        }
      }
      return theString.replaceAll("[ \t]+", " ");
    }
  }