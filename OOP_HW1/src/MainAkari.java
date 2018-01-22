import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alex on 20.01.2018.
 */
public class MainAkari {

    static int A;
    static int k;
    static int r;
    static int i;

    static int sscanf(Pattern p, String data) {
        Matcher m = p.matcher(data);
        if (!m.find()) return 0;
        k = Integer.parseInt(m.group(1));
        A = Integer.parseInt(m.group(2));
        i = Integer.parseInt(m.group(3));
        r = Integer.parseInt(m.group(4));
        return 4;
    }

    public static void main(String[] args) {
        InputStream p;
        OutputStream q;
        int a_ = 0;
        String format = "P(\\d*)\n(\\d*) (\\d*)\n(\\d*)\n";
        Pattern pattern = Pattern.compile(format);
        char [] d = new char[format.length()];
                format.getChars(0, format.length(), d, 0);
        byte[] b = new byte[1024];
        char[] y = new char[300];
        int u;

        int n = args.length + 1;
        try {
            p = (args.length > 0 && (args[0].charAt(0) != '-' || args[0].charAt(1) != '\0')) ? new FileInputStream(args[0]) : System.in;
            q = (args.length < 2 || !(args[1].charAt(0) != '-' || args[1].charAt(1) != 0)) ? System.out : new FileOutputStream(args[1]);
        } catch (FileNotFoundException e) {
            System.out.println(String.format("Could not open file due to exception: %s", e.toString()));
            return;
        }

        ("vwcceapvgeX`ihkkjwffcmeahhmnddvffe``gqng`X`aooXXhhqthh`psjXXh`kujXhibbllhhbbxlfgdc`afffd``fgppri``gqX]f`hra<pfis<ifatga<" +
                "afuh+fafvv+fg`wja+`iik>bi+b+lrbrxylrfyg>daca`aaafufuf9dr`y`3figupap_rBiM`P`OgWq^X*]/f]`;hvroai<dp/f*i*s/<ii(f)a{tpguat<c" +
                "ahfaurh(+uf)a;f}vivn+tf/g*`*w/jmaa+i`ni(i+k[>+b+i>++b++>l[rb").getChars(0, 300, y, 0);
        try {
            if ((a_ = p.read(b)) > 2 && b[0] == 'P' && 4 == sscanf(pattern, new String(b)) && k == 5 || k == 6 && r == 255)
            {
                u = A;
                if (n > 3) {
                    u++;
                    i++;
                }
                q.write(String.format("P%d\n%d %d\n%d\n", k, u >> 1, i >> 1, r).getBytes());
                q.flush();
                u = k != 5 ? 8 : 4;
                k = 3;
            } else{
                (u) = n + 14 > 17 ? 8 / 4 : 8 * 5 / 4;
            }

            for (r = i = 0; ; ) {
                u *= 6;
                u += (n > 3 ? 1 : 0);
                if ((y[u] & 01) != 0)
                    q.write(r);
                if ((y[u] & 16) != 0)
                    k = A;
                if ((y[u] & 2) != 0)
                    k--;
                if (i >= a_) {
                    i = (u) * 11 & 255;
                    if ((a_ = p.read(b)) <= 0)
                        break;
                    i = 0;
                }
                r = b[i];
                i++;
                u += ((8 & y[u]) != 0) ? (r != 10 ? 4 : 2) :((y[u] & 4) != 0) ? (k != 0 ? 2 : 4) : 2;
                u = y[u] - (int) '`';
            }
        } catch (IOException e) {
            ;
        }
        try {
            p.close();
            q.close();
        } catch (IOException e) {
            ;
        }
    }
}
