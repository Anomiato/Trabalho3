public static void testeAtraso(Date date) {	
        List<Loan> loanList = FileOperations.ReadLoanCSV(filenameLoan);
		List<Loan> atrasos = new ArrayList<Loan>();
        for(Loan l:loanList)
			if(l.getDateF.before(date))
				if(l.LoanBook.StatusLoan!=0)
					atrasos.add(l);
		for(Loan l:atrasos)
			FileOperations.SaveCSV(l.toCSV(), arrears.csv, 1);
}
			
		
