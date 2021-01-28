package by.grodno.pvt.site.housingAndCommunalServicesApp.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workPlan_table")
public class WorkPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "requestForm_id")
    private RequestForm requestForm;
    @OneToOne
    @JoinColumn(name = "workBrigade_id")
    private WorkBrigade workBrigade;
    private Boolean work_completed;
}
