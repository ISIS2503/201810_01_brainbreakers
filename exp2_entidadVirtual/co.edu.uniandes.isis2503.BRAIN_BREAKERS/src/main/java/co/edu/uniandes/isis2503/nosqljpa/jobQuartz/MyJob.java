/*
 * The MIT License
 *
 * Copyright 2018 Universidad De Los Andes - Departamento de Ingeniería de Sistemas.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.isis2503.nosqljpa.jobQuartz;

import co.edu.uniandes.isis2503.nosqljpa.model.entity.ResidenciaEntity;
import co.edu.uniandes.isis2503.nosqljpa.persistence.ResidenciaPersistence;
import co.edu.uniandes.isis2503.nosqljpa.service.WiringService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author aa.yepes
 */
public class MyJob implements org.quartz.Job {

    
    private ResidenciaPersistence persistResidencia;
    private WiringService serviceW;
    
    public MyJob() {
        persistResidencia = new ResidenciaPersistence();
        serviceW =  new WiringService();
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("se esta haciendo la verificacion de las contrasenias");

        for (ResidenciaEntity resi : persistResidencia.all()) {
            for (String horario : resi.getHorarios()) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date fechaInic = format.parse(horario.split(";")[0]);
                    Date fechaFin = format.parse(horario.split(";")[1]);
                    Date fechaAct = new Date();
                    if (fechaFin.compareTo(fechaAct) == -1) {
                        serviceW.deleteAllPasswords();
                    } else if (fechaInic.compareTo(fechaAct) == -1 && fechaFin.compareTo(fechaAct) == 1) {

                    }

                } catch (Exception e) {

                    throw new JobExecutionException("la actualizacion fallo");
                }

            }
        }
    }

}
