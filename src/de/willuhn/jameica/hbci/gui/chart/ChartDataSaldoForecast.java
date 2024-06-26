/**********************************************************************
 *
 * Copyright (c) 2004 Olaf Willuhn
 * All rights reserved.
 * 
 * This software is copyrighted work licensed under the terms of the
 * Jameica License.  Please consult the file "LICENSE" for details. 
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.gui.chart;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import de.willuhn.jameica.hbci.forecast.ForecastCreator;
import de.willuhn.jameica.hbci.rmi.Konto;
import de.willuhn.jameica.hbci.server.Value;

/**
 * Implementierung eines Datensatzes fuer die Darstellung der Saldo-Prognose.
 */
public class ChartDataSaldoForecast extends AbstractChartDataSaldo
{
  private Konto konto      = null;
  private Date end         = null;
  private List<Value> data = null;
  
  /**
   * ct.
   * @param k das Konto, fuer das das Diagramm gemalt werden soll.
   * @param end End-Datum. Die Prognose beginnt heute und reicht bis zum angegeben End-Datum.
   */
  public ChartDataSaldoForecast(Konto k, Date end)
  {
    this.konto = k;
    this.end   = end;
  }

  /**
   * @see de.willuhn.jameica.hbci.gui.chart.ChartData#getData()
   */
  public List getData() throws RemoteException
  {
    if (this.data != null)
      return this.data;
    
    this.data = ForecastCreator.create(this.konto,this.end);
    return this.data;
  }
  
  /**
   * @see de.willuhn.jameica.hbci.gui.chart.AbstractChartDataSaldo#getLineStyle()
   */
  @Override
  public LineStyle getLineStyle() throws RemoteException
  {
    return LineStyle.DOT;
  }
  
  /**
   * @see de.willuhn.jameica.hbci.gui.chart.AbstractChartDataSaldo#isFilled()
   */
  @Override
  public boolean isFilled() throws RemoteException
  {
    return true;
  }
  
  /**
   * @see de.willuhn.jameica.hbci.gui.chart.AbstractChartDataSaldo#isLegendEnabled()
   */
  @Override
  public boolean isLegendEnabled() throws RemoteException
  {
    return false;
  }

  /**
   * @see de.willuhn.jameica.hbci.gui.chart.ChartData#getLabel()
   */
  public String getLabel() throws RemoteException
  {
    final String s = (this.konto != null) ? this.konto.getBezeichnung() : i18n.tr("Alle Konten");
    return s + ": " + i18n.tr("Prognose");
  }
}
