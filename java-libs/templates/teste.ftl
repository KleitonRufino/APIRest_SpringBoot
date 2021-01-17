${data?string("dd/MM/yyyy")}

Prezado ${usuario}, segue a lista de produtos:

<#list produtos>
<#items as p>
${p.nome} - R${p.preco} <#sep> and</#sep>
</#items>
<#else>
No Items
</#list>
 
 -----------
#DATA
${data?string("dd/MM/yyyy")}
#Referência Objeto
${usuario}
#Lista/If/ElseIf/Else/Filter/Starts_With:
 <#list produtos?filter(p ->p.nome?starts_with("Produto")) as p>
 <#if (p.preco>=400 && p.preco<=500)>
* ${p.nome?upper_case} - R${p.preco}
<#elseif (p.preco>500)>
# ${p.nome} - R${p.preco}	
 <#else>
- ${p.nome} - R${p.preco}
</#if>
 </#list>
 