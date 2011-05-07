<?
   ////////////////////////////////////////////////////////
   //
   // SCRIPT USAGE:
   //
   //   TRANSFER URL: http://SERVER_BASE_URL/transfer.php
   //
   //   (SERVER_BASE_URL is defined in the plugin config)
   //
   //   PARAMETERS:
   //
   //     f : (required) Script function to run (urlencoded).
   //         Currently available options:
   //           upload, download, list (see usage below)
   //
   //     a : (optional) Auth password for user-protected /
   //         private structures in the repository.
   //
   //     p : Player name (urlencoded)
   //
   //     n : Filename for the saved structure (urlencoded)
   //
   //     d : The actual block data as a large urlencoded
   //         string joined by the colon (":") character
   //
   ////////////////////////////////////////////////////////
   //
   // FUNCTION USAGE SYNTAX:
   //
   //   upload:
   //
   //     Required parameters:
   //       f = "upload",
   //       p = owner name,
   //       n = filename,
   //       d = data
   //
   //       Access is user-configured via the website. The
   //       plugin should notify the user of the URL to manage
   //       their uploaded file after the transfer completes.
   //
   //   download:
   //
   //     Required parameters:
   //
   //       If downloading a file marked PUBLIC:
   //         f = "download"
   //         n = filename OR n = filename@username
   //
   //       If downloading a private file:
   //         f = "download"
   //         n = filename@username,
   //         a = auth password (specified by owner)
   //
   //   list:
   //
   //     Required parameters:
   //
   //       If listing public files:
   //         f = "list"
   //
   //       If listing private and/or specific user files:
   //         f = "list"
   //         p = username of who is requesting the list
   //         n = username of the owner of the list being requested
   //         a = auth password (specified by owner)
   //
   //     Optional parameters:
   //
   //       s = start# (for long lists - 5 per pg is probably best in chat)
   //       l = length (how many to list starting from s)
   //
   ////////////////////////////////////////////////////////
   //
   // RETURNED ERROR CODES:
   //
   //   000 OK
   //     successful transfer completed.
   //
   //   001 CHUNKFILE NOT FOUND
   //     couldn't open chunkfile for loading
   //
   //   002 UNABLE TO SAVE CHUNKFILE
   //     couldn't open local chunkfile for saving
   //
   //   003 ACCESS DENIED
   //     access denial for private chunkfiles
   //
   //   004 GURU MEDITATION ERROR
   //     sent for generic failures. Probably unused.
   //
   //   005 INVALID POST SYNTAX
   //     sent if the POSTvars are incorrect/malformed
   //
   ////////////////////////////////////////////////////////

   if( !file_exists( "./assets/" ) )
   {
     mkdir( "./assets" );

     if( file_exists( "./assets/" ) )
     {
       $fw = fopen( "./assets/index.php", "w" );
       if( $fw )
       {
         fputs( $fw, "Location: /\n\r\n\r" );
         fclose( $fw );
       }
     }
   }

   if( isset( $_POST["f"] ) && isset( $_POST["p"] )
    && isset( $_POST["n"] && isset( $_POST["d"] ) )
   {
     if( $_POST["f"] == "upload" )
     {
       // Receive and save the file..

       $filename = $_POST["n"];
       $filedata = explode( ":", $_POST["d"] );
       $playername = $_POST["p"];

       // Strip out unhealthy characters..
       //
       $badchars = "[^0-9a-zA-z()_-]";
       $filename = preg_replace( "/^[.]*/","",$filename );
       $filename = preg_replace( "/[.]*$/","",$filename );
       $filename = preg_replace( "/$badchars/","_",$filename );
       $playername = preg_replace( "/^[.]*/","",$playername );
       $playername = preg_replace( "/[.]*$/","",$playername );
       $playername = preg_replace( "/$badchars/","_",$playername );

       if( !file_exists( "./assets/$playername" ) )
       {
         mkdir( "./assets/$playername" );

         if( file_exists( "./assets/$playername" ) )
         {
           $fw = fopen( "./assets/$playername/index.php", "w" );
           if( $fw )
           {
             fputs( $fw, "Location: /\n\r\n\r" );
             fclose( $fw );
           }
         }
       }

       if( !file_exists( "./assets/$playername/$filename.chunk" ) )
       {
         $fw = fopen( "./assets/$playername/$filename.chunk", "w" );

         if( $fw )
         {
           foreach( $filedata as $item )
           {
             fputs( $fw, $item . "\n" );
           }

           fclose( $fw );
         }
         else
           echo( "002 UNABLE TO SAVE CHUNKFILE\n" );
       }
     }
     elseif( $_POST["f"] == "download" )
     {
       // do the download stuff..
     }
     else
       echo( "009 INVALID SYNTAX\n" );
   }
   else
     echo( "009 INVALID SYNTAX\n" );
?>