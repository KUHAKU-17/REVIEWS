package packMain;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;



public class gemSticker {

    public void created(InputStream inputStream, String nomeArquivo)throws Exception {

        /*Leitura da imagem*/
        /*InputStream imputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies_1.jpg").openStream(); */
        BufferedImage imagemOriginal = ImageIO.read(inputStream);


        /* Criar nova imagem em memoria por transparencia e com tamanho novo */
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        /* Copiar a imagem original pra nova imagem(em memoria) */
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,0,0, null);

        /* Configurar a fonte */
        var fonte = new Font("Impact", Font.BOLD, 100);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        /* Escrever uma frase na nova imagem */
        String texto = "PABLO APROVA!";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;
        int posicaoTextoY = novaAltura - 100;
        graphics.drawString(texto, posicaoTextoX, novaAltura - 100);


        FontRenderContext fontRenderContext = graphics.getFontRenderContext();

        var textLayout = new TextLayout(texto, fonte, fontRenderContext);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.black);
        graphics.draw(outline);
        graphics.setClip(outline);

        /* Escrever a nova imagem em um arquivo */
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }
}
