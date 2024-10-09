
package com.mycompany.empresa;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import javax.swing.JFileChooser;
import java.io.File;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GeneradorPDF {

    // Método que maneja la creación del PDF
    private void crearPDF(JTable tabla, String filePath, String titulo) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime now = LocalDateTime.now();

            // Usar el título dinámico
            document.add(new Paragraph(titulo).setBold().setFontSize(16));
            document.add(new Paragraph("Este informe fue generado el " + now.format(dateFormatter) + " a las " + now.format(timeFormatter) + ".").setMarginBottom(15));

            int columnCount = tabla.getColumnCount();
            float[] columnWidths = new float[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnWidths[i] = 1;
            }
            Table pdfTable = new Table(columnWidths);

            for (int i = 0; i < columnCount; i++) {
                pdfTable.addHeaderCell(new Cell().add(new Paragraph(tabla.getColumnName(i)).setBold()));
            }

            TableModel model = tabla.getModel();
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object value = model.getValueAt(row, col);
                    pdfTable.addCell(new Cell().add(new Paragraph(value == null ? "" : value.toString())));
                }
            }

            document.add(pdfTable);
            document.close();
            
            JOptionPane.showMessageDialog(null, "Archivo PDF creado exitosamente en: " + filePath);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el archivo PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método que se llama para exportar la tabla en el formato elegido con un título dinámico
    public void exportarTabla(JTable tabla, String titulo) {
        // Obtener la fecha y hora actuales
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = now.format(dateFormatter);
        String currentTime = now.format(timeFormatter);

        // Definir el nombre predeterminado del archivo
        String defaultFileName = "Datos_" + currentDate + "_" + currentTime;

        // Crear un cuadro de diálogo para elegir entre PDF o Excel
        Object[] options = {"Guardar como PDF", "Guardar como Excel"};
        int choice = JOptionPane.showOptionDialog(null, "¿En qué formato deseas guardar?", "Seleccionar Formato",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            // Guardar como PDF
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar PDF");
            fileChooser.setSelectedFile(new File(System.getProperty("user.home") + "/Desktop/" + defaultFileName + ".pdf"));

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String pdfPath = fileToSave.getAbsolutePath();
                if (!pdfPath.toLowerCase().endsWith(".pdf")) {
                    pdfPath += ".pdf";
                }

                // Llamar al método para crear el PDF con el título dinámico
                crearPDF(tabla, pdfPath, titulo);

            } else {
                JOptionPane.showMessageDialog(null, "Guardado cancelado.");
            }

        } else if (choice == 1) {
            // Guardar como Excel
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Excel");
            fileChooser.setSelectedFile(new File(System.getProperty("user.home") + "/Desktop/" + defaultFileName + ".xlsx"));

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String excelPath = fileToSave.getAbsolutePath();
                if (!excelPath.toLowerCase().endsWith(".xlsx")) {
                    excelPath += ".xlsx";
                }

                try (Workbook workbook = new XSSFWorkbook()) {
                    Sheet sheet = workbook.createSheet("Datos");

                    // Usar el título dinámico en la primera fila
                    Row titleRow = sheet.createRow(0);
                    titleRow.createCell(0).setCellValue(titulo);

                    // Crear encabezados dinámicos
                    Row header = sheet.createRow(1);
                    for (int col = 0; col < tabla.getColumnCount(); col++) {
                        header.createCell(col).setCellValue(tabla.getColumnName(col));
                    }

                    // Obtener el modelo de la tabla y agregar las filas al Excel
                    TableModel model = tabla.getModel();
                    for (int row = 0; row < model.getRowCount(); row++) {
                        Row excelRow = sheet.createRow(row + 2);
                        for (int col = 0; col < model.getColumnCount(); col++) {
                            Object value = model.getValueAt(row, col);
                            if (value instanceof Integer) {
                                excelRow.createCell(col).setCellValue((Integer) value);
                            } else if (value instanceof String) {
                                excelRow.createCell(col).setCellValue((String) value);
                            } else {
                                excelRow.createCell(col).setCellValue(value != null ? value.toString() : "");
                            }
                        }
                    }

                    // Ajustar el tamaño de las columnas
                    for (int col = 0; col < tabla.getColumnCount(); col++) {
                        sheet.autoSizeColumn(col);
                    }

                    // Escribir el archivo
                    try (FileOutputStream fileOut = new FileOutputStream(excelPath)) {
                        workbook.write(fileOut);
                    }

                    JOptionPane.showMessageDialog(null, "Archivo Excel creado exitosamente en: " + excelPath);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al crear el archivo Excel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Guardado cancelado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ninguna opción seleccionada.");
        }
    }
}


