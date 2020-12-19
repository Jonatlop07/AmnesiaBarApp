 package amnesiabar.utilidad;

import javafx.scene.control.TextField;

/** Extiende la clase TextField garantizando el manejo del número máximo de caracteres que se pueden escribir en este
 *
 * @author Jonathan López
 */
public class CampoLimitadoTexto extends TextField {
    private final int limite;
    
    public CampoLimitadoTexto(int limite){
        this.limite = limite;
    }
    
    @Override
    public void replaceText(int inicio, int fin, String texto){
        super.replaceText(inicio, fin, texto);
        verificar();
    }
    
    @Override
    public void replaceSelection(String texto){
        super.replaceSelection(texto);
        verificar();
    }
    
    private void verificar(){
        if (getText().length() > limite)
            setText(getText().substring(0, limite));
    }
}
