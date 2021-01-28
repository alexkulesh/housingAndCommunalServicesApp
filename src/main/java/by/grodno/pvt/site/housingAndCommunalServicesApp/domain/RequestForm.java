package by.grodno.pvt.site.housingAndCommunalServicesApp.domain;

import javax.persistence.*;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "request_form_table")
public class RequestForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date completionDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private WorkScale heatSupplyWorkScale;
    
    private WorkScale powerSupplyWorkScale;

    private WorkScale waterSupplyWorkScale;

    @Override
    public String toString() {
        return "" + id + "";
    }
}
