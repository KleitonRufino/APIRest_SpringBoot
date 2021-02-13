package freemaker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class FreemarkerUtils {

 private static Configuration cfg = new Configuration();

 private static final String TEMPLATES_FOLDER = "templates";

 public static final String parseTemplate(Map<String, Object> map,String templateName)
 throws TemplateException, IOException {

 //diret�rio onde est�o templates
 cfg.setDirectoryForTemplateLoading(new File(TEMPLATES_FOLDER));

 cfg.setObjectWrapper(new DefaultObjectWrapper());

 //recupera o template
 Template t = cfg.getTemplate(templateName);

 StringWriter writer = new StringWriter();

 /** Freemarker **/
 t.process(map, writer);

 writer.flush();
 writer.close();

 return writer.toString();
 }
}