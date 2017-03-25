import javafx.scene.paint.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryCrosshairState;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;
/**
 * Created by lucky on 14-02-2017.
 */
public class Project2 extends JFrame implements ActionListener {
 JButton Genrate,MakeChart;
 JTextField Field;
 JLabel B1;
 int LOL=0;
    Map<String,Integer> MP1=new TreeMap<>();
 Project2()
 {
     Field= new JTextField();
    Genrate= new JButton("Genrate");
     MakeChart=new JButton("MakeChart");
     B1=new JLabel("MAKE CHART AFTER FIND THE VALUE");
     Genrate.setBounds(30,100,100,30);
     B1.setBounds(30,150,250,30);
     MakeChart.setBounds(30,200,100,30);
     Field.setBounds(180,100,100,30);
     Genrate.addActionListener(this);
     MakeChart.addActionListener(this);
     Field.addActionListener(this);
     add(Genrate);add(MakeChart);add(Field);add(B1);
     setSize(600,600);
     setLayout(null);
     setVisible(true);
 }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    if(actionEvent.getActionCommand()=="Genrate")
    {
        int Y=1000;
        //Scanner sc=new Scanner(System.in);
        while (Y-->0)
        {
            Map<String,Integer> MP =new TreeMap<>();
            int X=100000;
            while (X-->0)
            {
                Random rand = new Random();
                String id = String.format("%04d", rand.nextInt(9999));
                if(!(MP.containsKey(id)))
                    MP.put(id,1);
                else
                    MP.put(id, MP.get(id) + 1);
            }
            Set<Map.Entry<String, Integer>> set = MP.entrySet();
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(set);
            Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
            {
                public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
                {
                    return (o2.getValue()).compareTo( o1.getValue() );
                }
            } );
            int x=0;
            for(Map.Entry<String, Integer> entry:list){
                if(x==10)
                {
                    break;
                }
               System.out.println(entry.getKey()+" "+entry.getValue());
                if(!(MP1.containsKey(entry.getKey())))
                    MP1.put(entry.getKey(),entry.getValue());
                else
                    MP1.put(entry.getKey(), MP1.get(entry.getKey()) + entry.getValue());
                x++;
            }
            MP.clear();

            System.out.println("Map is clear");
        }
        System.out.println("Analysis");
        Set<Map.Entry<String, Integer>> Set = MP1.entrySet();
        List<Map.Entry<String, Integer>> list1 = new ArrayList<Map.Entry<String, Integer>>(Set);
        Collections.sort( list1, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> O1, Map.Entry<String, Integer> O2 )
            {
                return (O2.getValue()).compareTo( O1.getValue() );
            }
        } );
        for(Map.Entry<String, Integer> Entry:list1){
           // System.out.println(Entry.getKey()+" "+Entry.getValue());
            Field.setText(Entry.getKey()+" "+String.valueOf(Entry.getValue()));
            LOL=Entry.getValue();
            break;
        }
    }
    if(actionEvent.getActionCommand()=="MakeChart")
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
       /* Set<Map.Entry<String, Integer>> Set = MP1.entrySet();
        List<Map.Entry<String, Integer>> list1 = new ArrayList<Map.Entry<String, Integer>>(Set);
        Collections.sort( list1, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> O1, Map.Entry<String, Integer> O2 )
            {
                return (O2.getValue()).compareTo( O1.getValue() );
            }
        } );
        int z=0;
        for(Map.Entry<String, Integer> Entry:list1){
            if(z!=0) {
                dataset.setValue(Entry.getValue(), "value", Entry.getKey());
                z++;
            }
            else
                dataset.setValue(Entry.getValue(), "value1", Entry.getKey());
            z++;
        }*/
       for(String key :MP1.keySet())
       {
           if(MP1.get(key)!=LOL)
           {
               dataset.setValue(MP1.get(key), "value1", key);
           }
           else
           {
               dataset.setValue(MP1.get(key), "value", key);
           }
       }
        JFreeChart jchat= ChartFactory.createBarChart("Record","Values","Occurences",dataset, PlotOrientation.VERTICAL,true,true,false);
        CategoryPlot Plot=jchat.getCategoryPlot();
        jchat.setBackgroundPaint(Color.cyan);
        Plot.setRangeGridlinePaint(Color.CYAN);
        ChartFrame chartfg=new ChartFrame("Record",jchat,true);
        BarRenderer renderer = (BarRenderer) Plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(231,175,61));
        renderer.setSeriesPaint(1, Color.blue);
        chartfg.setVisible(true);
        chartfg.setSize(300,300);
       // ChartPanel panel=new ChartPanel(jchat);
       // pnlReport.remove
    }
    }
    public static void main(String args[]){
        Project2 ob=new Project2();

    }
}
