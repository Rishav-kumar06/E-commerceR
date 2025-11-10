Place required dependency JARs here so NetBeans/Ant can package them into the WAR.

Required JARs for Hibernate (recommended for this project):
- lib/byte-buddy-1.14.19.jar
- (optional) lib/byte-buddy-agent-1.14.19.jar

Quick steps (PowerShell) to download and install Byte Buddy into the project:

# 1) Download Byte Buddy jars from Maven Central
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy/1.14.19/byte-buddy-1.14.19.jar" -OutFile "lib\byte-buddy-1.14.19.jar"
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy-agent/1.14.19/byte-buddy-agent-1.14.19.jar" -OutFile "lib\byte-buddy-agent-1.14.19.jar"

# 2) Copy into the webapp WEB-INF/lib so the running Tomcat picks them up (project layout)
Copy-Item -Path "lib\byte-buddy-1.14.19.jar" -Destination "web\WEB-INF\lib\"
Copy-Item -Path "lib\byte-buddy-agent-1.14.19.jar" -Destination "web\WEB-INF\lib\"

# 3) Clean and rebuild the project (Ant)
cd "C:\Users\rehan\Desktop\WebApplication"
ant clean
ant

# 4) Verify the WAR contains the byte-buddy jar
# (assumes dist/WebApplication.war or build/WebApplication.war depending on your build)
jar tf build\web\WebApplication.war | Select-String "byte-buddy"

# 5) Redeploy to Tomcat, restart server, then test login (john@example.com / password123)

Notes and alternatives
- If NetBeans references an absolute path for `file.reference.byte-buddy-1.14.19.jar`, either place the jar at that absolute path or update `nbproject/project.properties` to reference a relative `lib/` path (so the project is portable). If you want, I can update `nbproject` to use the relative path; however I didn't change it automatically to avoid touching machine-specific settings.
- If you cannot add Byte Buddy, you can force Hibernate to use Javassist by adding this to `hibernate.cfg.xml`:
  <property name="hibernate.bytecode.provider">javassist</property>
  But be careful: the project currently uses `javassist-3.13.0-GA` which may be incompatible with Hibernate 5.6; prefer adding Byte Buddy.
- If Tomcat has conflicting jars in ${TOMCAT_HOME}/lib, remove duplicates to avoid classloader conflicts.

If you'd like, I can also update `nbproject/project.properties` to use the relative `lib/byte-buddy-1.14.19.jar` path and create the `lib/` directory (already created). After you place the jar(s) into `lib/`, rebuilding will include them.