<? include( "config.php" ); ?><style type="text/css">

html
{
  height: 100%;
  margin: 0px;
}

body
{
  height: 100%;
  margin: 0px;
  background: <?=$body_colour?>;
  color: <?=$text_colour?>;
}

a:link
{
  cursor: hand;
  text-decoration: none;
  font-weight: bold;
  color: <?=$link_lcolour?>;
}
a:visited
{
  cursor: hand;
  text-decoration: none;
  font-weight: bold;
  color: <?=$link_vcolour?>;
}
a:hover
{
  cursor: hand;
  text-decoration: none;
  font-weight: bold;
  color: <?=$link_hcolour?>;
}
a:active
{
  cursor: hand;
  text-decoration: none;
  font-weight: bold;
  color: <?=$link_acolour?>;
}

.hdrInfo
{
  font-family: Tahoma,"Sans Serif",Helvetica;
  vertical-align: top;
  text-align: left;
  font-size: 16px;
  font-weight: normal;
}

.pVersion
{
  font-size: 11px;
  font-weight: bold;
  font-style: italic;
  display: inline;
  position: relative;
  left: -20px;
  top: -6px;
}

.tblMain
{
  font-family: Tahoma,"Sans Serif",Helvetica;
  width: 100%;
  height: 100%;
  border: none;
  margin: 0px;
}

.mainContent
{
  height: 100%;
}

.tblContent
{
  width: 100%;
  height: 100%;
  background: #8080ff;
  border-top: solid 2px #c0c0ff;
  border-left: solid 2px #c0c0ff;
  border-bottom: solid 2px #404040;
  border-right: solid 2px #404040;
  margin-left: auto;
  margin-right: auto;
}

.tdContent
{
  height: 100%;
  text-align: left;
  vertical-align: top;
}

.tblMenu
{
  color: #ffffff;
  text-align: left;
  text-align: left;
  background: #000080;
  border: none;
  width: 100%;
  height: 32px;
}

.tdMenuMain
{
  vertical-align: top;
}

.tdMenu
{
  font-weight: bold;
  background: #0000c0;
  text-align: center;
  vertical-align: middle;
}

</style>