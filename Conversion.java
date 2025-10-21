// Aviso : esse código é uma conversão feito por IA do meu código em kotlin se possivel avaliar os arquivos .kt, desde ja agradeço. 

public class Main {

    // --- MAIN ---
    // Ponto de entrada do programa, contendo os casos de teste
    public static void main(String[] args) {
        System.out.println("first input:");
        testeNavigation(
            new Browser(),
            new String[] {
                "get-current",
                "access,https://amazon.com",
                "access,https://cnn.com",
                "get-current",
                "back",
                "get-current",
                "back",
                "get-current",
                "back"
            }
        );
        System.out.println();
        System.out.println("second input:");
        testeNavigation(
            new Browser(),
            new String[] {
                "access,https://amazon.com",
                "access,https://cnn.com",
                "get-current",
                "forward"
            }
        );
        System.out.println();
        System.out.println("third input:");
        testeNavigation(
            new Browser(),
            new String[] {
                "access,https://amazon.com",
                "access,https://cnn.com",
                "access,https://gmail.com",
                "access,https://outlook.com",
                "get-current",
                "back",
                "back",
                "back",
                "get-current",
                "forward",
                "forward",
                "get-current"
            }
        );
        System.out.println();
        System.out.println("fourth input:");
        testeNavigation(
            new Browser(),
            new String[] {
                "access,https://amazon.com",
                "access,https://cnn.com",
                "access,https://gmail.com",
                "access,https://outlook.com",
                "get-current",
                "back",
                "back",
                "back",
                "get-current",
                "forward",
                "forward",
                "get-current",
                "access,https://devsuperior.com.br",
                "back",
                "forward",
                "get-current",
                "forward"
            }
        );
    }

    // --- TESTE NAVIGATION ---
    // Função estática que executa os comandos de teste
    public static void testeNavigation(Browser browser, String[] commands) {
        // Itera sobre cada comando usando um loop for-each
        for (String command : commands) {
            // Usa if-else if para simular o 'when' do Kotlin
            if (command.equals("get-current")) {
                System.out.println(browser.getCurrentPage());
            } else if (command.equals("back")) {
                try {
                    browser.back();
                } catch (Exception e) {
                    // Em Java, capturamos Exception (ou RuntimeException)
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (command.equals("forward")) {
                try {
                    browser.forward();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (command.startsWith("access")) {
                // O split funciona da mesma forma
                String[] newArr = command.split(",");
                browser.access(newArr[1]);
            } else {
                System.out.println("Invalid Command: " + command);
            }
        }
    }
}

// --- PAGE ---
// Conversão da 'data class Page' para uma classe Java simples.
// Esta classe não é 'public' para poder ficar no mesmo arquivo que 'Main'.
class Page {
    public final String url; // 'val' se torna 'final'
    public Page nextPage;    // 'var' permanece um campo mutável

    // Construtor para definir a URL (nextPage é null por padrão em Java)
    public Page(String url) {
        this.url = url;
        this.nextPage = null;
    }
}

// --- NAVIGATION STACK ---
// Conversão da classe 'NavigationStack'
class NavigationStack {
    private int size = 0;
    private Page head = null;

    // Getters públicos para os campos privados (simulando 'private set' do Kotlin)
    public int getSize() {
        return size;
    }

    public Page getHead() {
        return head;
    }

    // (push)
    public void access(String url) {
        Page newPage = new Page(url);
        newPage.nextPage = this.head;
        this.head = newPage;
        this.size++;
    }

    // (pop)
    public Page back() {
        if (this.size == 0) return null; // ou (this.head == null)
        
        Page pageToReturn = this.head;
        this.head = this.head.nextPage; // Não precisamos do 'safe call' (?.), pois sabemos que 'head' não é nulo
        pageToReturn.nextPage = null; // Limpa a referência
        this.size--;
        return pageToReturn;
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }
}

// --- BROWSER ---
// Conversão da classe 'Browser'
class Browser {
    private String currentPage = "home";
    // 'val' se torna 'final'
    private final NavigationStack backStack = new NavigationStack();
    private final NavigationStack forwardStack = new NavigationStack();

    public String getCurrentPage() {
        return this.currentPage;
    }

    public void access(String url) {
        this.backStack.access(this.currentPage);
        this.currentPage = url;
        this.forwardStack.clear();
    }

    public void back() {
        // Em Java, é mais idiomático usar RuntimeException do que Error
        if (this.backStack.getHead() == null) {
            throw new RuntimeException("Back Error");
        }
        this.forwardStack.access(this.currentPage);
        Page current = this.backStack.back();
        
        // Simulação do operador Elvis (current?.url ?: "home")
        this.currentPage = (current != null) ? current.url : "home";
    }

    public void forward() {
        if (this.forwardStack.getHead() == null) {
            throw new RuntimeException("Forward Error");
        }
        this.backStack.access(this.currentPage);
        Page current = this.forwardStack.back();
        
        // Simulação do operador Elvis
        this.currentPage = (current != null) ? current.url : "home";
    }
}
