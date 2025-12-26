package librarymanagement.client;

/**
 *
 * @author XuanDat
 */
import librarymanagement.client.functions.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MenuUI extends JFrame {
    
    private AddController addCtrl;
    private EditController editCtrl;
    private DeleteController delCtrl;
    private SearchController searchCtrl;
    private StatsController statsCtrl;

    private DefaultTableModel model;
    private JTable table;
    private JTextField txtId, txtTitle, txtValue;
    private JComboBox<String> cbType;

    public MenuUI() {
        addCtrl = new AddController();
        editCtrl = new EditController();
        delCtrl = new DeleteController();
        searchCtrl = new SearchController();
        statsCtrl = new StatsController();
        initUI();
        refreshTable(searchCtrl.getAll());
    }

    private void initUI() {
        setTitle("Library System");
        setSize(850, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel pTop = new JPanel(new GridLayout(4, 2));
        txtId = new JTextField();
        txtTitle = new JTextField();
        txtValue = new JTextField();
        cbType = new JComboBox<>(new String[]{"Book", "DVD", "Magazine"});

        pTop.add(new JLabel(" ID:")); pTop.add(txtId);
        pTop.add(new JLabel(" Title:")); pTop.add(txtTitle);
        pTop.add(new JLabel(" Type:")); pTop.add(cbType);
        pTop.add(new JLabel(" Pages/Duration:")); pTop.add(txtValue);
        add(pTop, "North");

        model = new DefaultTableModel(new String[]{"ID", "Title", "Type", "Value"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        add(new JScrollPane(table), "Center");

        JPanel pBot = new JPanel();
        JButton addBtn = new JButton("1. Add");
        JButton detailBtn = new JButton("2. Detail");
        JButton editBtn = new JButton("3. Edit");
        JButton deleteBtn = new JButton("4. Delete");
        JButton searchBtn = new JButton("5. Search");
        JButton countBtn = new JButton("6. Count");
        JButton feeBtn = new JButton("7. Fee > $2");

        pBot.add(addBtn); 
        pBot.add(detailBtn); 
        pBot.add(editBtn); 
        pBot.add(deleteBtn);
        pBot.add(searchBtn); 
        pBot.add(countBtn); 
        pBot.add(feeBtn);
        add(pBot, "South");

        addBtn.addActionListener(e -> {
            try {
                addCtrl.addItem(txtId.getText(), txtTitle.getText(), 
                        cbType.getSelectedItem().toString(), txtValue.getText());
                refreshTable(searchCtrl.getAll());
                txtId.setText(""); txtTitle.setText(""); txtValue.setText("");
                JOptionPane.showMessageDialog(this, "Added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        detailBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            ItemDTO i = editCtrl.getItemDTO(row);
            if (i != null) JOptionPane.showMessageDialog(this, "ID: " + i.getId() + "\nTitle: " 
                    + i.getTitle() + "\nType: " + i.getType() + "\nValue: " + i.getValue());
            else JOptionPane.showMessageDialog(this, "Select row!");
        });

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            ItemDTO currentItem = editCtrl.getItemDTO(row);
            
            if (currentItem != null) {
                JTextField editTitleField = new JTextField(currentItem.getTitle());
                JComboBox<String> editTypeBox = new JComboBox<>(new String[]{"Book", "DVD", "Magazine"});
                editTypeBox.setSelectedItem(currentItem.getType()); // Chọn sẵn type cũ
                JTextField editValueField = new JTextField(String.valueOf(currentItem.getValue()));

                JPanel panel = new JPanel(new GridLayout(4, 2));
                panel.add(new JLabel("Title:")); panel.add(editTitleField);
                panel.add(new JLabel("Type:")); panel.add(editTypeBox);
                panel.add(new JLabel("Value:")); panel.add(editValueField);

                int result = JOptionPane.showConfirmDialog(this, panel, 
                        "Edit Item | ID: " + currentItem.getId(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        editCtrl.updateItem(
                            row,
                            String.valueOf(currentItem.getId()),
                            editTitleField.getText(), 
                            editTypeBox.getSelectedItem().toString(), 
                            editValueField.getText()
                        );
                        refreshTable(searchCtrl.getAll());
                        JOptionPane.showMessageDialog(this, "Updated Successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to edit!");
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Delete?") == 0) {
                delCtrl.deleteItem(row);
                refreshTable(searchCtrl.getAll());
            } else if (row == -1) JOptionPane.showMessageDialog(this, "Select row!");
        });

        searchBtn.addActionListener(e -> {
            String k = JOptionPane.showInputDialog(this, "Keyword:");
            if (k != null) refreshTable(searchCtrl.searchByTitle(k));
        });

        countBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, statsCtrl.getCountReport()));

        feeBtn.addActionListener(e -> {
            String d = JOptionPane.showInputDialog(this, "Days:");
            if(d != null) {
                try {
                    JOptionPane.showMessageDialog(this, statsCtrl.getFeeReport(d));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        });
    }

    private void refreshTable(List<ItemDTO> list) {
        model.setRowCount(0);
        for (ItemDTO i : list) model.addRow(new Object[]{i.getId(), i.getTitle(), i.getType(), i.getValue()});
    }
}