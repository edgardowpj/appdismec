package com.example.practi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

public class PdfViewerActivity extends AppCompatActivity {

    ProgressBar progressBar;
    PDFView pdfView;
    int position;
    TextView tvid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        pdfView = findViewById(R.id.pdf_viewer);
        progressBar = findViewById(R.id.progressBar);
        tvid = findViewById(R.id.txtid);


        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");


        tvid.setText(ListPDFActivity.pdfs.get(position).getTitle());
        final String pdf = tvid.getText().toString().trim();
        Log.d("Test",pdf);
        new  GetPdfViewer(pdfView,progressBar).execute(pdf);
    }
}