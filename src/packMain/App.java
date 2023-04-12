package packMain;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception{

        // fazer uma conexão HTTP e buscar os top 250 filmes
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoImdb();

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        //String url= "http://localhost:8080/linguagens";//
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoImdb();//

        var http = new ClientHttp();
        String json = http.searchData(url);

        // exibir e manipular os dados

        List<Conteudo>conteudos = extrator.extraiConteudos(json);

        for (int i = 0; i < 2; i++) {

            Conteudo conteudo = conteudos.get(i);


            InputStream imputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeDoArquivo ="saida/"+ conteudo.getTitulo() + ".png";

            var geradora = new gemSticker();
            geradora.created(imputStream, nomeDoArquivo);

            System.out.println(conteudo.getTitulo());

            }
            System.out.println("\n");

        }

    }

