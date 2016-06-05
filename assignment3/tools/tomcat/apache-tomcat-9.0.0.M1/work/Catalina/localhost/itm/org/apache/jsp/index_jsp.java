/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.0.M1
 * Generated at: 2016-06-04 21:25:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.io.*;
import java.net.*;
import itm.image.*;
import itm.model.*;
import itm.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("java.net");
    _jspx_imports_packages.add("itm.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("java.io");
    _jspx_imports_packages.add("itm.image");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("itm.model");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--\r\n");
      out.write("/*******************************************************************************\r\n");
      out.write(" This file is part of the WM.II.ITM course 2016\r\n");
      out.write(" (c) University of Vienna 2009-2016\r\n");
      out.write(" *******************************************************************************/\r\n");
      out.write("-->\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"js/raphael.js\"></script>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"js/jquery-2.1.4.min.js\"></script>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"js/dracula_graffle.js\"></script>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"js/dracula_graph.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <h1>Welcome to the ITM media library</h1>\r\n");
      out.write("        <a href=\"graph.jsp\">graph</a>\r\n");
      out.write("         \r\n");
      out.write("        \r\n");
      out.write("        ");

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
                c++;
                
      out.write("\r\n");
      out.write("                    <div style=\"width:500px;height:500px;padding:10px;float:left;\">\r\n");
      out.write("\r\n");
      out.write("                ");

            
                // handle images
                if ( medium instanceof ImageMedia ) {
                	 // ***************************************************************
                    //  Fill in your code here!
                    // ***************************************************************
                    
                    // show the histogram of the image on mouse-over
                    
                    // display image thumbnail and metadata
                    ImageMedia img = (ImageMedia) medium;
                    
      out.write("\r\n");
      out.write("                    <div style=\"width:200px;height:200px;padding:10px;\">\r\n");
      out.write("\t                    <div style=\"width: 700px; height: 200px;\">\r\n");
      out.write("\t                        <a href=\"media/img/");
      out.print( img.getInstance().getName());
      out.write("\">\r\n");
      out.write("\t                        \t<img src=\"media/md/");
      out.print( img.getInstance().getName() );
      out.write(".thumb.png\" border=\"0\" />\r\n");
      out.write("\t                        </a>\r\n");
      out.write("\t                         <a href=\"media/md/");
      out.print( img.getInstance().getName());
      out.write(".hist.png\">\r\n");
      out.write("\t                        \t<img src=\"media/md/");
      out.print( img.getInstance().getName());
      out.write(".hist.png\" border=\"0\" height=\"30%\" width=\"30%\"/>\r\n");
      out.write("\t                        </a>\r\n");
      out.write("\t                    </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div>\r\n");
      out.write("                        Name: ");
      out.print( img.getName() );
      out.write("<br/>\r\n");
      out.write("                        Dimensions: ");
      out.print( img.getWidth() );
      out.write('x');
      out.print( img.getHeight() );
      out.write("px<br/>\r\n");
      out.write("                        ");
 if(img.getSize() != 0){  
      out.write("Size: ");
      out.print( img.getSize() );
      out.write(" B (");
      out.print( img.getSize()/1024 );
      out.write(" kB)  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(img.getComponentsNumber() != 0){  
      out.write(" numComponents: ");
      out.print( img.getComponentsNumber() );
      out.write(" <br/> ");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t\t\tnumColorComponents: ");
      out.print( img.getColorNumber() );
      out.write(" <br/>\r\n");
      out.write("\t\t\t\t\t\ttransparency: ");
      out.print( img.getTransparency() );
      out.write("  <br/>\r\n");
      out.write("\t\t\t\t\t\t");
 if(img.getPixelSize() != 0){  
      out.write(" pixelSize: ");
      out.print( img.getPixelSize() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t\t\torientation: ");
      out.print( img.getOrientation() );
      out.write("  <br/> \r\n");
      out.write("\t\t\t\t\t\tcolorSpaceType: ");
      out.print( img.getColorSpaceType() );
      out.write("  <br/>\r\n");
      out.write("                        Tags: ");
 for ( String t : img.getTags() ) { 
      out.write("<a href=\"tags.jsp?tag=");
      out.print( t );
      out.write('"');
      out.write('>');
      out.print( t );
      out.write("</a> ");
 } 
      out.write("<br/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    ");
  
                    } else 
                if ( medium instanceof AudioMedia ) {
                    // display audio thumbnail and metadata
                    AudioMedia audio = (AudioMedia) medium;
                    
      out.write("\r\n");
      out.write("                    <div style=\"width:200px;height:200px;padding:10px;\">\r\n");
      out.write("                        <br/><br/><br/><br/>\r\n");
      out.write("                        <embed src=\"media/md/");
      out.print( audio.getInstance().getName() );
      out.write(".wav\" autostart=\"false\" width=\"150\" height=\"30\" />\r\n");
      out.write("                        <br/>\r\n");
      out.write("                        <a href=\"media/audio/");
      out.print( audio.getInstance().getName());
      out.write("\">\r\n");
      out.write("                            Download ");
      out.print( audio.getInstance().getName());
      out.write("\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div>\r\n");
      out.write("                        Name: ");
      out.print( audio.getName() );
      out.write("<br/>\r\n");
      out.write("                        ");
 if(audio.getDurationMinS() != null){  
      out.write(" Duration: ");
      out.print( audio.getDurationMinS() );
      out.write(" [mm:ss] <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(audio.getSize() != 0){  
      out.write("Size: ");
      out.print( audio.getSize() );
      out.write(" B (");
      out.print( audio.getSize()/1024 );
      out.write(" kB)  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(audio.getAuthor() != null){  
      out.write(" author: ");
      out.print( audio.getAuthor() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(audio.getTitle() != null){  
      out.write(" title: ");
      out.print( audio.getTitle() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(audio.getCopyright() != null){  
      out.write(" copyright: ");
      out.print( audio.getCopyright() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(audio.getComment() != null){  
      out.write(" comment: ");
      out.print( audio.getComment() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(audio.getAlbum() != null){  
      out.write(" album: ");
      out.print( audio.getAlbum() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(audio.getDate() != null){  
      out.write(" date: ");
      out.print( audio.getDate() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        \r\n");
      out.write("                        ");
 if(audio.getTrack() != null){  
      out.write(" track: ");
      out.print( audio.getTrack() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(audio.getComposer() != null){  
      out.write(" composer: ");
      out.print( audio.getComposer() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(audio.getGenre() != null){  
      out.write(" genre: ");
      out.print( audio.getGenre() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("                        ");
 if(audio.getEncoding() != null){  
      out.write(" encoding: ");
      out.print( audio.getEncoding() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        \r\n");
      out.write("                        ");
 if(audio.getSampleRate() != null){  
      out.write(" sampleRate: ");
      out.print( audio.getSampleRate() );
      out.write(" Hz  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        \r\n");
      out.write("                        ");
 if(audio.getBitrate() != null){  
      out.write(" bitrate: ");
      out.print( audio.getBitrate() );
      out.write(" kb/s <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(audio.getChannels() != null){  
      out.write(" channels: ");
      out.print( audio.getChannels() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        \r\n");
      out.write("                        \r\n");
      out.write("                        Tags: ");
 for ( String t : audio.getTags() ) { 
      out.write("<a href=\"tags.jsp?tag=");
      out.print( t );
      out.write('"');
      out.write('>');
      out.print( t );
      out.write("</a> ");
 } 
      out.write("<br/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    ");
  
                    } else
                if ( medium instanceof VideoMedia ) {
                    // handle videos thumbnail and metadata...
                    VideoMedia video = (VideoMedia) medium;
                    
      out.write("\r\n");
      out.write("                    <div style=\"width:200px;height:200px;padding:10px;\">\r\n");
      out.write("                        <a href=\"media/video/");
      out.print( video.getInstance().getName());
      out.write("\">\r\n");
      out.write("                            \r\n");
      out.write("                        <object width=\"200\" height=\"200\">\r\n");
      out.write("                            <param name=\"movie\" value=\"media/md/");
      out.print( video.getInstance().getName() );
      out.write("_thumb.avi\">\r\n");
      out.write("                            <embed src=\"media/md/");
      out.print( video.getInstance().getName() );
      out.write("_thumb.avi\" width=\"200\" height=\"200\">\r\n");
      out.write("                            </embed>\r\n");
      out.write("                        </object>\r\n");
      out.write("\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div>\r\n");
      out.write("                        Name: <a href=\"media/video/");
      out.print( video.getInstance().getName());
      out.write('"');
      out.write('>');
      out.print( video.getName() );
      out.write("</a><br/>\r\n");
      out.write("                        Duration: ");
      out.print( video.getVideoLength() );
      out.write(" seconds<br/>\r\n");
      out.write("                        ");
 if(video.getSize() != 0){  
      out.write("Size: ");
      out.print( video.getSize() );
      out.write(" B (");
      out.print( video.getSize()/1024 );
      out.write(" kB)  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(video.getVideoCodec() != null){  
      out.write(" videoCodec: ");
      out.print( video.getVideoCodec() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(video.getVideoCodecID() != null){  
      out.write(" videoCodecID: ");
      out.print( video.getVideoCodecID() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(video.getVideoFrameRate() != null){  
      out.write(" videoFrameRate: ");
      out.print( video.getVideoFrameRate() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(video.getVideoHeight() != null){  
      out.write(" videoHeight: ");
      out.print( video.getVideoHeight() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(video.getVideoWidth() != null){  
      out.write(" videoWidth: ");
      out.print( video.getVideoWidth() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        \r\n");
      out.write("                        ");
 if(video.getAudioCodec() != null){  
      out.write(" audioCodec: ");
      out.print( video.getAudioCodec() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(video.getVideoCodecID() != null){  
      out.write(" audioCodecID: ");
      out.print( video.getVideoCodecID() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(video.getAudioChannels() != null){  
      out.write(" audioChannels: ");
      out.print( video.getAudioChannels() );
      out.write("  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(video.getAudioSampleRate() != null){  
      out.write(" audioSampleRate: ");
      out.print( video.getAudioSampleRate() );
      out.write(" Hz  <br/> ");
 } 
      out.write("\r\n");
      out.write("                        ");
 if(video.getAudioBitRate() != null){  
      out.write(" audioBitRate: ");
      out.print( video.getAudioBitRate() );
      out.write(" kb/s <br/> ");
 } 
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("                        Tags: ");
 for ( String t : video.getTags() ) { 
      out.write("<a href=\"tags.jsp?tag=");
      out.print( t );
      out.write('"');
      out.write('>');
      out.print( t );
      out.write("</a> ");
 } 
      out.write("<br/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    ");
  
                    } else {
                        }

                
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                ");

                    if ( c % 3 == 0 ) {
                
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    <div style=\"clear:left\"/>\r\n");
      out.write("                ");

                        }

                } // for 
                
        
      out.write("\r\n");
      out.write("        \r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
