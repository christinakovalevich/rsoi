<?xml version="1.0" encoding="windows-1251"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <h1><b><center><font color="#ff0000">Товары:</font></center></b></h1>
        <table border="5">
            <xsl:for-each select="/goods/good">
                <tr bgcolor="cyan"><td colspan="3" width="600">
                    <h2><font color="brown"><xsl:value-of select="@ID"/></font></h2>
                </td></tr>

                <xsl:for-each select="type">
                    <tr>
                        <td colspan="3" width="600"><center><h3><xsl:value-of 								select="@ID"/></h3></center></td>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="code"/></td>
                        <td><xsl:value-of select="model"/></td>
                        <td><xsl:value-of select="price"/></td>
                    </tr>
                </xsl:for-each>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>
