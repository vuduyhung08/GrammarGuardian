

document.getElementById('fileInput').addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('text').value = e.target.result;
        };
        if (file.type === "text/plain") {
            reader.readAsText(file);
        } else if (file.name.endsWith('.docx')) {
            readDocxFile(file);
        }
    }
});

function readDocxFile(file) {
    const JSZip = require('jszip');
    const mammoth = require('mammoth');

    JSZip.loadAsync(file).then(function(zip) {
        const docxFiles = Object.keys(zip.files).filter(fn => fn.endsWith('.xml'));
        const docxPromises = docxFiles.map(fn => zip.files[fn].async('text'));

        Promise.all(docxPromises).then(function(contents) {
            mammoth.extractRawText({ arrayBuffer: contents[0] })
                .then(function(result) {
                    document.getElementById('text').value = result.value;
                })
                .catch(function(err) {
                    console.error('Error reading DOCX:', err);
                });
        });
    });
}
