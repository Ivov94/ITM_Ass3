<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="itm.image.*" %>
<%@ page import="itm.model.*" %>
<%@ page import="itm.util.*" %>
<!--
/*******************************************************************************
 This file is part of the WM.II.ITM course 2016
 (c) University of Vienna 2009-2016
 *******************************************************************************/
-->
<html>
    <head>
        <script type="text/javascript" src="js/raphael.js"></script>
        <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="js/dracula_graffle.js"></script>
        <script type="text/javascript" src="js/dracula_graph.js"></script>
    </head>
    <body>
        <%
            // get the file paths - this is NOT good style (resources should be loaded via inputstreams...)
            // we use it here for the sake of simplicity.
            String basePath = getServletConfig().getServletContext().getRealPath( "media"  );
            if ( basePath == null )
                throw new NullPointerException( "could not determine base path of media directory! please set manually in JSP file!" );
            File base = new File( basePath );
            File imageDir = new File( basePath, "img");
            File audioDir = new File( basePath, "audio");
            File videoDir = new File( basePath, "video");
            File metadataDir = new File( basePath, "md");
            MediaFactory.init( imageDir, audioDir, videoDir, metadataDir );
            
            // get all media objects
            ArrayList<AbstractMedia> media = MediaFactory.getMedia();
    
            // ***************************************************************
            //  Fill in your code here!
            // ***************************************************************
            ArrayList<String> allTags = new ArrayList<String>();
        %>
        <div id="canvas" style="height: 1024px; width: 1024px; margin: auto; display: block; text-align:center;"></div>
        <script>
        
            var g = new Graph();
            g.addNode("Tags", "", "/itm/tags.jsp");
        <%
        	for ( AbstractMedia medium : media ) {
        		ArrayList<String> tagsInMedium = medium.getTags();
        		for (String tag : tagsInMedium){
        			if(!(allTags.contains(tag))){
        				allTags.add(tag);
        			}
        		}
            }
        
        	for(String tag : allTags){
        		String tagURL = "/itm/tags.jsp?tag=" + tag;
        %>
            g.addNode("<%= tag %>", "", "<%= tagURL %>");
            g.addEdge("Tags", "<%= tag %>", "");
        <%
        	}
        
            for ( AbstractMedia medium : media ) {
            	String mediumURL = "media";
            	if(medium.getTags().contains("image")){
            		mediumURL += "/img/";
            	} else if(medium.getTags().contains("audio")){
            		mediumURL += "/audio/";
            	} else if(medium.getTags().contains("video")){
            		mediumURL += "/video/";
            	}
            	mediumURL += medium.getName();
        %>
            	g.addNode("<%= medium.getName()  %>", "", "<%= mediumURL %>");
    	<%	
            	
            	if(medium.getTags().isEmpty()){
            		%>
            		
        			g.addEdge("<%= medium.getName()  %>", "Tags", "");
        			
					<%
            	}
            	else{
	            	for(String tag : allTags){
	            		if(medium.getTags().contains(tag)){

        %>
        		
                			g.addEdge("<%= medium.getName()  %>", "<%= tag %>", "");
        <%
		
						}
            		}
            	}
            }
        %>
            var layouter = new Graph.Layout.Spring(g);
            layouter.layout();
            
            var renderer = new Graph.Renderer.Raphael('canvas', g, 1024, 1024);
            renderer.draw();
        </script> 
    </body>
</html>