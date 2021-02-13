${data?string("dd/MM/yyyy")}

Prezado ${usuario}, segue a lista de produtos:

<#list produtos>
<#items as p>
${p.nome} - R${p.preco!0} <#sep> and</#sep>
</#items>
<#else>
No Items
</#list>
 
 -----------
 <#-- Comentario -->
#DATA: ${data?string("dd/MM/yyyy")}
#Referência Objeto: ${usuario}
#SIZE: ${produtos?size}
#LENGTH: ${produtos[0].nome?length}
#BOOLEAN: ${produtos[0].ativo?string("Y", "N")}
#JOIN: ${produtos?join(", ")}
#VALOR DEFAULT: Welcome ${usuario2!"USER"}!
#CHECK !NULL: <#if usuario??><h1>Welcome ${usuario}!</h1></#if>
#CHECK !NULL: <#if usuario2??><h1>Welcome ${usuario2}!</h1></#if>
#CONCATENACAO: ${'Produto: ' + produtos[0]["nome"]}
#CRIAR VARIAVEL: <#assign s = "Hello ${usuario}!"> ${s}
#OBTENDO CARACTER: ${usuario[0]}${usuario[0..3]}
#CRIANDO LISTA:  <#list ["foo", "bar", "baz"] as x> ${x} </#list>
#Lista/If/ElseIf/Else/Filter/Starts_With/UPPER_CASE:
<#list produtos?filter(p ->p.nome?starts_with("Produto")) as p>
INDEX: ${p?index}
COUNTER: ${p?counter} 
 <#if (p.preco?? && p.preco>=400 && p.preco<=500)>
* ${p.nome?upper_case} - R$${p.preco!0}
<#elseif (p.preco?? && p.preco>500)>
# ${p.nome} - R$${p.preco!0}	
 <#else>
- ${p.nome} - R$${p.preco!0}
</#if>
 </#list>
 