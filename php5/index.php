<? include( "config.php" ); ?><html>
<head>

<title><?=$script_title?><?=$script_dversion?></title>

<link rel="stylesheet" type="text/css" href="style.css">

</head>

<body>

<table class="tblMain">
<tr><th class="hdrLogo">
<a href="/"><img border="0" src="<?=$logo_image?>"></a><p class="pVersion"><?=$script_version?></p></th>
<th class="hdrInfo">Welcome to the <? include("1word.php"); ?> structure repository. In order to view private structures, you must login using your Minecraft account.  Otherwise, feel free to browse the repository anonymously. Please enjoy your visit.</th></tr>

<tr class="mainContent"><td colspan="2">
<? include( "content.php" ); ?>
</td></tr>

</table>

</body>

</html>