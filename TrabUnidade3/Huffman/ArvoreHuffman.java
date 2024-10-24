package Huffman;

public class ArvoreHuffman {

    public NodeHuffman raiz;
    private char[] caracteres;
    private String[] codigos;
    private int indice;
    HeapMinimo heapMinimo;

    public void contarCaractereFrequencia(String mensagem, char[] arrayCaracteres, int[] arrayFrequencias) {
        int numCaracteresUnicos = 0;

        for (int i = 0; i < mensagem.length(); i++) {
            char caractereAtual = mensagem.charAt(i);

            int indice = buscarIndice(arrayCaracteres, numCaracteresUnicos, caractereAtual);

            if (indice == -1) {
                arrayCaracteres[numCaracteresUnicos] = caractereAtual;
                arrayFrequencias[numCaracteresUnicos] = 1;
                numCaracteresUnicos++;
            } else {
                arrayFrequencias[indice]++;
            }
        }
    }

    //! tinha no codigo base, o resto nao
    public void construirArvore(char[] arrayCaracteres, int[] arrayFrequencias) {
        caracteres = new char[arrayCaracteres.length];
        codigos = new String[arrayCaracteres.length];
        heapMinimo = new HeapMinimo(arrayCaracteres.length);
        
        for (int i = 0; i < arrayCaracteres.length; i++) {
            NodeHuffman no = new NodeHuffman(arrayFrequencias[i], arrayCaracteres[i]);
            heapMinimo.inserir(no);
        }
        
        while (heapMinimo.tamanho() > 1) {
            NodeHuffman x = heapMinimo.removerMinimo();
            NodeHuffman y = heapMinimo.removerMinimo();
            
            NodeHuffman z = new NodeHuffman(x.freq + y.freq, '-');
            z.esquerda = x;
            z.direita = y;
            
            heapMinimo.inserir(z);
        }
        
        raiz = heapMinimo.removerMinimo();
        
        gerarCodigos(raiz, "");
    }
    
    private void gerarCodigos(NodeHuffman no, String codigo) {
        if (no == null) {
            return;
        }
        
        if (no.esquerda == null && no.direita == null && isCharValido(no.caractere)) {
            caracteres[indice] = no.caractere;
            codigos[indice] = codigo;
            indice++;
        }
        
        gerarCodigos(no.esquerda, codigo + "0");
        gerarCodigos(no.direita, codigo + "1");
    }
    
    public String comprimir(String texto) {
        StringBuilder codigoHuffman = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caractereAtual = texto.charAt(i);
            for (int j = 0; j < caracteres.length; j++) {
                if (caracteres[j] == caractereAtual) {
                    codigoHuffman.append(codigos[j]);
                    break;
                }
            }
        }
        
        return codigoHuffman.toString();
    }
    
    public String descomprimir(String codigo) {
        StringBuilder textoDescomprimido = new StringBuilder();
        NodeHuffman atual = raiz;
        
        for (int i = 0; i < codigo.length(); i++) {
            char bit = codigo.charAt(i);
            
            if (bit == '0') {
                atual = atual.esquerda;
            } else if (bit == '1') {
                atual = atual.direita;
            }
            
            if (atual.esquerda == null && atual.direita == null) {
                textoDescomprimido.append(atual.caractere);
                atual = raiz;
            }
        }
        
        return textoDescomprimido.toString();
    }
    
    public void imprimirCodigo(NodeHuffman no, String s) {
        if (no == null) {
            return;
        }
        
        if (no.esquerda == null && no.direita == null && isCharValido(no.caractere)) {
            return;
        }
        
        imprimirCodigo(no.esquerda, s + "0");
        imprimirCodigo(no.direita, s + "1");
    }
    
    private boolean isCharValido(char c) {
        return (Character.isLetterOrDigit(c) || c == ' ' || c == ':');
    }

    public void imprimirCodigos() {
        imprimirCodigo(raiz, "");
    }
    
    private int buscarIndice(char[] arrayCaracteres, int tamanhoAtual, char caractere) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (arrayCaracteres[i] == caractere) {
                return i;
            }
        }
        return -1;
    }
}
