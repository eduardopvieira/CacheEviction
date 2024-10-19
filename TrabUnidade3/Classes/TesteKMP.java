package Classes;

public class TesteKMP {

    public static void main(String[] args) {
        KMP kmp = new KMP();
        
        // Teste 1: Caso básico
        String texto1 = "ababcabcabababd";
        String padrao1 = "ababd";
        System.out.println("Teste 1: Procurando padrão '" + padrao1 + "' no texto '" + texto1 + "'");
        kmp.buscar(padrao1, texto1);  // Deve encontrar o padrão no índice 10
        System.out.println();

        // Teste 2: Padrão no início do texto
        String texto2 = "aaaaab";
        String padrao2 = "aaaa";
        System.out.println("Teste 2: Procurando padrão '" + padrao2 + "' no texto '" + texto2 + "'");
        kmp.buscar(padrao2, texto2);  // Deve encontrar o padrão no índice 0
        System.out.println();

        // Teste 3: Padrão no final do texto
        String texto3 = "abcdeabcdf";
        String padrao3 = "abcdf";
        System.out.println("Teste 3: Procurando padrão '" + padrao3 + "' no texto '" + texto3 + "'");
        kmp.buscar(padrao3, texto3);  // Deve encontrar o padrão no índice 5
        System.out.println();

        // Teste 4: Padrão não encontrado
        String texto4 = "abcabcabc";
        String padrao4 = "def";
        System.out.println("Teste 4: Procurando padrão '" + padrao4 + "' no texto '" + texto4 + "'");
        kmp.buscar(padrao4, texto4);  // Não deve encontrar o padrão
        System.out.println();

        // Teste 5: Padrão com repetição
        String texto5 = "abxabcabcaby";
        String padrao5 = "abcaby";
        System.out.println("Teste 5: Procurando padrão '" + padrao5 + "' no texto '" + texto5 + "'");
        kmp.buscar(padrao5, texto5);  // Deve encontrar o padrão no índice 6
        System.out.println();
    }
}
