<#--
<form></form>
-->
<#macro form
action="" method="post" target="" enctype="" acceptCharset="" width="100%" tableClass="am-table am-table-striped am-table-hover table-main" labelWidth="20" required="false" colspan="1"
validate="false" query="true" beanName=""
id="" name="" class="" style="" size="" title="" disabled="" tabindex="" accesskey=""
onsubmit=""
>
<form class="am-form" <#rt/>
        method="${method}"<#rt/>
        action="${action}"<#rt/>
    <#if id!=""> id="${id}"</#if><#rt/>
    <#if target!=""> target="${target}"</#if><#rt/>
    <#if enctype!=""> enctype="${enctype}"</#if><#rt/>
    <#if onsubmit!=""> onsubmit="${onsubmit}"</#if><#rt/>
    <#if acceptCharset!=""> accept-charset="${acceptCharset}"</#if><#rt/>
    <#include "common-attributes.ftl"/><#rt/>
        >
    <#if query=="true">
        <@p.hidden name="pageNo" /><#rt/>
        <#list .data_model?keys as pkey>
            <#if pkey?starts_with('query')>
                <@p.hidden name="${pkey}" /><#t/>
            </#if>
        </#list>
    </#if>

        <#assign labelWidth=labelWidth/>
    <table width="${width}" class="${tableClass}" cellpadding="2" cellspacing="1" border="0">
    <tr>

    <#nested/><#rt/>

    </tr></table>

</form>
</#macro>
