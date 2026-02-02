package aston.java.intensive.module5.utils.menu;

import java.io.PrintStream;

public class Banner {
    private static final String BANNER = """
             _|    _|    _|_|      _|_|_|  _|      _|  _|_|_|  _|      
             _|  _|    _|    _|  _|        _|_|    _|    _|    _|       
             _|_|      _|_|_|_|    _|_|    _|  _|  _|    _|    _|      
             _|  _|    _|    _|        _|  _|    _|_|    _|    _|      
             _|    _|  _|    _|  _|_|_|    _|      _|  _|_|_|  _|_|_|_|
			""";

    public void print(PrintStream printStream) {
        printStream.println();
        printStream.println(BANNER);
        printStream.println();
    }
}
