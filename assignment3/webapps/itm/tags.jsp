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
<%
       
%>
<html>
    <head>
    </head>
    <body>
        <%
        
            String paramTag = null;

            // ***************************************************************
            //  Fill in your code here!
            // ***************************************************************

            // get "tag" parameter
            paramTag = request.getParameter("tag");
            
            // if no param was passed, forward to index.jsp (using jsp:forward)
            if(paramTag == null){
         %>	
         <jsp:forward page="index.jsp" />
            	
         <%   	
         }else{
            	
            %>	   	

       

        <h1>Media that is tagged with <%= paramTag %></h1>
        <a href="index.jsp">back</a>

        <%

            // ***************************************************************
            //  Fill in your code here!
            // ***************************************************************
        
            // get all media objects that are tagged with the passed tag
            
            // iterate over all available media objects and display them
            
            
        
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
        
        int c=0; // counter for rowbreak after 3 thumbnails.
        // iterate over all available media objects
        for ( AbstractMedia medium : media ) {

            // handle images
            if (!medium.getTags().contains(paramTag))
            	continue;
            
            c++;
            
            %>
            <div style="width:500px;height:500px;padding:10px;float:left;">

        	<%
            
            
            if ( medium instanceof ImageMedia) {

            	 // ***************************************************************
                //  Fill in your code here!
                // ***************************************************************
                
                // show the histogram of the image on mouse-over
                
                // display image thumbnail and metadata
                ImageMedia img = (ImageMedia) medium;
                %>
                    <div style="width:200px;height:200px;padding:10px;">
	                    <div style="width: 700px; height: 200px;">
	                        <a href="media/img/<%= img.getInstance().getName()%>">
	                        	<img src="media/md/<%= img.getInstance().getName() %>.thumb.png" border="0" />
	                        </a>
	                         <a href="media/md/<%= img.getInstance().getName()%>.hist.png">
	                        	<img src="media/md/<%= img.getInstance().getName()%>.hist.png" border="0" height="30%" width="30%"/>
	                        </a>
	                    </div>
                    </div>
                <div>
                    Name: <%= img.getName() %><br/>
                    Dimensions: <%= img.getWidth() %>x<%= img.getHeight() %>px<br/>
                    <% if(img.getSize() != 0){  %>Size: <%= img.getSize() %> B (<%= img.getSize()/1024 %> kB)  <br/> <% } %>
                    <% if(img.getComponentsNumber() != 0){  %> numComponents: <%= img.getComponentsNumber() %> <br/> <% } %>
					numColorComponents: <%= img.getColorNumber() %> <br/>
					transparency: <%= img.getTransparency() %>  <br/>
					<% if(img.getPixelSize() != 0){  %> pixelSize: <%= img.getPixelSize() %>  <br/> <% } %>
					orientation: <%= img.getOrientation() %>  <br/> 
					colorSpaceType: <%= img.getColorSpaceType() %>  <br/>
                    Tags: <% for ( String t : img.getTags() ) { %><a href="tags.jsp?tag=<%= t %>"><%= t %></a> <% } %><br/>
                    <a href="media/md/<%= img.getInstance().getName()%>.hist.png">Histogram</a>
                </div>
                <%  
                } else 
                    if ( medium instanceof AudioMedia ) {
                        // display audio thumbnail and metadata
                        AudioMedia audio = (AudioMedia) medium;
                        %>
                        <div style="width:200px;height:200px;padding:10px;">
                            <br/><br/><br/><br/>
                            <embed src="media/md/<%= audio.getInstance().getName() %>.wav" autostart="false" width="150" height="30" />
                            <br/>
                            <a href="media/audio/<%= audio.getInstance().getName()%>">
                                Download <%= audio.getInstance().getName()%>
                            </a>
                        </div>
                        <div>
                            Name: <%= audio.getName() %><br/>
                            <% if(audio.getDurationMinS() != null){  %> Duration: <%= audio.getDurationMinS() %> [mm:ss] <br/> <% } %>
                            <% if(audio.getSize() != 0){  %>Size: <%= audio.getSize() %> B (<%= audio.getSize()/1024 %> kB)  <br/> <% } %>
                            <% if(audio.getAuthor() != null){  %> author: <%= audio.getAuthor() %>  <br/> <% } %>
                            <% if(audio.getTitle() != null){  %> title: <%= audio.getTitle() %>  <br/> <% } %>
                            <% if(audio.getCopyright() != null){  %> copyright: <%= audio.getCopyright() %>  <br/> <% } %>
                            <% if(audio.getComment() != null){  %> comment: <%= audio.getComment() %>  <br/> <% } %>
                            <% if(audio.getAlbum() != null){  %> album: <%= audio.getAlbum() %>  <br/> <% } %>
                            <% if(audio.getDate() != null){  %> date: <%= audio.getDate() %>  <br/> <% } %>
                            
                            <% if(audio.getTrack() != null){  %> track: <%= audio.getTrack() %>  <br/> <% } %>
                            <% if(audio.getComposer() != null){  %> composer: <%= audio.getComposer() %>  <br/> <% } %>
                            <% if(audio.getGenre() != null){  %> genre: <%= audio.getGenre() %>  <br/> <% } %>

                            <% if(audio.getEncoding() != null){  %> encoding: <%= audio.getEncoding() %>  <br/> <% } %>
                            
                            <% if(audio.getSampleRate() != null){  %> sampleRate: <%= audio.getSampleRate() %> Hz  <br/> <% } %>
                            
                            <% if(audio.getBitrate() != null){  %> bitrate: <%= audio.getBitrate() %> kb/s <br/> <% } %>
                            <% if(audio.getChannels() != null){  %> channels: <%= audio.getChannels() %>  <br/> <% } %>
                            
                            
                            Tags: <% for ( String t : audio.getTags() ) { %><a href="tags.jsp?tag=<%= t %>"><%= t %></a> <% } %><br/>
                        </div>
                        <%  
                        } else
                    if ( medium instanceof VideoMedia ) {
                        // handle videos thumbnail and metadata...
                        VideoMedia video = (VideoMedia) medium;
                        %>
                        <div style="width:200px;height:200px;padding:10px;">
                            <a href="media/video/<%= video.getInstance().getName()%>">
                                
                            <object width="200" height="200">
                                <param name="movie" value="media/md/<%= video.getInstance().getName() %>_thumb.avi">
                                <embed src="media/md/<%= video.getInstance().getName() %>_thumb.avi" width="200" height="200">
                                </embed>
                            </object>

                            </a>
                        </div>
                        <div>
                            Name: <a href="media/video/<%= video.getInstance().getName()%>"><%= video.getName() %></a><br/>
                            Duration: <%= video.getVideoLength() %> seconds<br/>
                            <% if(video.getSize() != 0){  %>Size: <%= video.getSize() %> B (<%= video.getSize()/1024 %> kB)  <br/> <% } %>
                            <% if(video.getVideoCodec() != null){  %> videoCodec: <%= video.getVideoCodec() %>  <br/> <% } %>
                            <% if(video.getVideoCodecID() != null){  %> videoCodecID: <%= video.getVideoCodecID() %>  <br/> <% } %>
                            <% if(video.getVideoFrameRate() != null){  %> videoFrameRate: <%= video.getVideoFrameRate() %>  <br/> <% } %>
                            <% if(video.getVideoHeight() != null){  %> videoHeight: <%= video.getVideoHeight() %>  <br/> <% } %>
                            <% if(video.getVideoWidth() != null){  %> videoWidth: <%= video.getVideoWidth() %>  <br/> <% } %>
                            
                            <% if(video.getAudioCodec() != null){  %> audioCodec: <%= video.getAudioCodec() %>  <br/> <% } %>
                            <% if(video.getVideoCodecID() != null){  %> audioCodecID: <%= video.getVideoCodecID() %>  <br/> <% } %>
                            <% if(video.getAudioChannels() != null){  %> audioChannels: <%= video.getAudioChannels() %>  <br/> <% } %>
                            <% if(video.getAudioSampleRate() != null){  %> audioSampleRate: <%= video.getAudioSampleRate() %> Hz  <br/> <% } %>
                            <% if(video.getAudioBitRate() != null){  %> audioBitRate: <%= video.getAudioBitRate() %> kb/s <br/> <% } %>
    	
                            Tags: <% for ( String t : video.getTags() ) { %><a href="tags.jsp?tag=<%= t %>"><%= t %></a> <% } %><br/>
                        </div>
                        <%  
                        }
            
            
            
            
            %>
                </div>
            <%
                if ( c % 3 == 0 ) {
            %>

                <div style="clear:left"/>
            <%
                    }

            } // for 
                
        } //end else
        %>
        
    </body>
</html>
