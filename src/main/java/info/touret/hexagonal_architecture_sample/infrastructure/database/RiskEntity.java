package info.touret.hexagonal_architecture_sample.infrastructure.database;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "risks")
public class RiskEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    public RiskEntity(Long id, Long amountMax, Long amountMin, String ruleName, String message) {
        this.id = id;
        this.amountMax = amountMax;
        this.amountMin = amountMin;
        this.ruleName = ruleName;
        this.message = message;
    }

    public RiskEntity() {
    }

    @Column(name = "amountMax")
    private Long amountMax;
    @Column(name = "amountMin")
    private Long amountMin;
    @Column(name = "ruleName")
    private String ruleName;
    @Column(name = "message")
    private String message;

    public Long getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(Long amountMax) {
        this.amountMax = amountMax;
    }

    public Long getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(Long amountMin) {
        this.amountMin = amountMin;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
