
  import java.io.*;
  import java.util.*;
  
  
  public class GrammarMain2 {    
    public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the cse143 random sentence generator.");
        System.out.println();

        // open grammar file
        System.out.print("What is the name of the grammar file? ");
        String fileName = console.nextLine();
        Scanner input = new Scanner(new File(fileName));

        // read the grammar file and construct the grammar solver
        List<String> grammar = new ArrayList<String>();
        while (input.hasNextLine())
            grammar.add(input.nextLine());
        GrammarSolver solver = 
            new GrammarSolver(Collections.unmodifiableList(grammar));
        
        System.out.println(solver.grammarContains("<s>"));
        System.out.println(solver.grammarContains(null));
        
//        String[] array = solver.generate("<s>", -1);
//        for (int i = 0; i < array.length; i++ ) {
//          System.out.println(array[i]);
//        }
        String[] array3 = solver.generate("E", 1);
        for (int i = 0; i < array3.length; i++ ) {
          System.out.println(array3[i]);
        }
        String[] array2 = solver.generate(null, 5);
        for (int i = 0; i < array2.length; i++ ) {
          System.out.println(array2[i]);
        }
        
        
    }
    
    
  }