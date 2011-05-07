<table class="tblMenu">
<tr>
<?
   if( isset( $_GET["loc"] ) )
     $location = $_GET["loc"];
   else
     $location = "home";

   if( $location == "home" )
     echo( "<td class=\"tdMenu\">Home</td>\n" );
   else
     echo( "<td class=\"tdMenu\"><a href=\"/\">Home</a></td>\n" );

   if( $location == "list" )
     echo( "<td class=\"tdMenu\">Browse</td>\n" );
   else
     echo( "<td class=\"tdMenu\"><a href=\"?loc=list\">Browse</a></td>\n" );

   if( $location == "upload" )
     echo( "<td class=\"tdMenu\">Upload</td>\n" );
   else
     echo( "<td class=\"tdMenu\"><a href=\"?loc=upload\">Upload</a></td>\n" );

   if( $location == "manage" )
     echo( "<td class=\"tdMenu\">Manage</td>\n" );
   else
     echo( "<td class=\"tdMenu\"><a href=\"?loc=manage\">Manage</a></td>\n" );

   if( $logged_in == FALSE )
   {
     if( $location == "login" )
       echo( "<td class=\"tdMenu\">Log In</td>\n" );
     else
       echo( "<td class=\"tdMenu\"><a href=\"?loc=login\">Log In</a></td>\n" );
   }
   else
   {
     if( $location == "logout" )
       echo( "<td class=\"tdMenu\">Log Out</td>\n" );
     else
       echo( "<td class=\"tdMenu\"><a href=\"?loc=login\">Log Out</a></td>\n" );
   }
?>
</tr>
</table>
