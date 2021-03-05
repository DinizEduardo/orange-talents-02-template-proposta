package br.com.zup.proposta.biometrias;

public class BiometriaResponse {

    private Long id;

    private String digital;

    public BiometriaResponse(Biometria biometria) {
        this.digital = biometria.getDigital();
        this.id = biometria.getId();
    }

    public Long getId() {
        return id;
    }

    public String getDigital() {
        return digital;
    }
}
