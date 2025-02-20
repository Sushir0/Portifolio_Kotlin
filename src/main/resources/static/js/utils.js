function formatarDataPtBrParaISO(dataString) {
    // Mapeamento dos meses em português
    const meses = {
        'jan.': 'Jan',
        'fev.': 'Feb',
        'mar.': 'Mar',
        'abr.': 'Apr',
        'mai.': 'May',
        'jun.': 'Jun',
        'jul.': 'Jul',
        'ago.': 'Aug',
        'set.': 'Sep',
        'out.': 'Oct',
        'nov.': 'Nov',
        'dez.': 'Dec'
    };

    // Extrair partes da data usando regex
    const partes = dataString.match(/(\d{1,2}) de (\w{3,4})\. de (\d{4})/);
    
    if (!partes) {
        throw new Error('Formato de data inválido');
    }

    const dia = partes[1].padStart(2, '0');
    const mesPt = partes[2].toLowerCase();
    const ano = partes[3];
    
    // Converter mês para inglês
    const mesEn = meses[mesPt] || meses[mesPt + '.'];
    
    if (!mesEn) {
        console.error(`Mês inválido recebido: "${mesPt}"`);
        throw new Error(`Mês inválido: "${mesPt}" recebido em "${dataString}"`);
    }

    // Criar objeto Date
    const data = new Date(`${mesEn} ${dia}, ${ano}`);
    
    // Formatar para YYYY-MM-DD
    const isoDate = data.toISOString().split('T')[0];
    
    return isoDate;
}