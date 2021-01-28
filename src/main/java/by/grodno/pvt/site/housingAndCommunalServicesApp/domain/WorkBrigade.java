package by.grodno.pvt.site.housingAndCommunalServicesApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workBrigade_table")
public class WorkBrigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date workStartTime;
    private Date workEndTime;

    private Boolean isBusy = false;
    @OneToOne
    @JoinColumn(name = "plumber_id")
    private Worker plumber;
    @OneToOne
    @JoinColumn(name = "electrician_id")
    private Worker electrician;
    @OneToOne
    @JoinColumn(name = "repairer_id")
    private Worker repairer;
    @Override
    public String toString() {
        return "" + id + "";
    }
}
