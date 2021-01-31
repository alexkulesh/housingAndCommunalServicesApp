package by.grodno.pvt.site.housingAndCommunalServicesApp.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workers_table")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private WorkerRole workerRole;
    private Boolean isBusy = false;
    @ManyToOne
    @JoinColumn(name = "workBrigade_id")
    private WorkBrigade workBrigade;
    @Override
    public String toString() {
        return "" + id + "";
    }
}
