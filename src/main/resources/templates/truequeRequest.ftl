<html>

<head></head>

<body>
<div class="example-emails margin-bottom-50">
    <div width="100%" style="background: #eceff4; padding: 50px 20px; color: #514d6a;">
        <div style="max-width: 700px; margin: 0px auto; font-size: 14px">
            <table border="0" cellpadding="0" cellspacing="0" style="width: 100%; margin-bottom: 20px">
                <tr>
                    <td style="vertical-align: top;">
                        <img src="https://image.ibb.co/fvqFwk/logo_landing.png" style="height: 40px"/>
                    </td>
                </tr>
            </table>
            <div style="padding: 40px 40px 20px 40px; background: #fff;">
                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
                    <tbody>
                    <tr>
                        <td>
                            <p>Buenas ${nombreDestino} ${apellidoDestino}</p>
                            <p>Teniendo en cuenta tus preferencias, esta propuesta de trueque te podría interesar.</p>
                </table>
                <br/>
                <h5 style="margin-bottom: 20px; color: #24222f; font-weight: 600">Propuesta</h5>
                <h5 style="margin-bottom: 20px; color: #7c8780; font-weight: 600">Recibiras los siguientes items del
                    usuario ${userNombreOfertante} ${userApellidoOfertante}</h5>
                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
                <#list itemsPropuestos>
                    <tr>
                        <#items as itemPropuesto>
                            <td>
                                <img src="${itemPropuesto.photo1}" alt="Imagen"
                                     style="border-style: double; height: 65%; float: left;margin: 0.5em"/>
                            </td>
                            <#--<td>-->
                                <#--<h2>${itemPropuesto.name}</h2></br>-->
                                <#--<h3>${itemPropuesto.description}</h3></br>-->
                                <#--<h3>${itemPropuesto.category.name}</h3>-->
                            <#--</td>-->
                        </#items>
                    </tr>
                </#list>
                </table>
                <h5 style="margin-bottom: 20px; color: #24222f; font-weight: 600">Tus Items</h5>
                <h5 style="margin-bottom: 20px; color: #7c8780; font-weight: 600">Entregarías los siguientes items al
                    usuario ${userNombreDestino} ${userApellidoDestino}</h5>
                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
                    <tbody>
                    <#list itemsDemandados>
                    <tr>
                        <#items as itemDemandado>
                            <td>
                                <img src="${itemDemandado.photo1}" alt="Imagen"
                                     style="border-style: double; height: 65%; float: left;margin: 0.5em"/>
                            </td>
                            <#--<td>-->
                                <#--<h2>${itemDemandado.name}</h2></br>-->
                                <#--<h3>${itemDemandado.description}</h3></br>-->
                                <#--<h3>${itemDemandado.category.name}</h3>-->
                            <#--</td>-->
                        </#items>
                    </tr>
                    </#list>
                    </tbody>
                </table>
                <a href="${uri_confirm}"
                   style="display: inline-block; padding: 11px 30px; margin: 20px 0px 30px; font-size: 15px; color: #fff; background: #01a8fe; border-radius: 5px">
                    Ver Detalle del Trueque
                </a>
                </td>
                </tr>
                </tbody>
                </table>
            </div>
            <div style="text-align: center; font-size: 12px; color: #a09bb9; margin-top: 20px">
                <p>
                    OkTrueque Software Inc., Independencia 864, Cordoba, Argentina.
                    <br/>
                    www.oktrueque.com
                </p>
            </div>
        </div>
    </div>
</div>
</body>

</html>