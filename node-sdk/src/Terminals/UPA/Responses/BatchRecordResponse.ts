export class BatchRecordResponse {
  public batchId: number | null = null;
  public batchSeqNbr: number | null = null;
  public batchStatus: string | null = null;
  public openUtcDateTime: string | null = null;
  public closeUtcDateTime: string | null = null;
  public openTnxId: string | null = null;
  public totalAmount: number | null = null;
  public totalCount: number | null = null;
  public creditCnt: number | null = null;
  public creditAmt: number | null = null;
  public debitCnt: number | null = null;
  public debitAmt: number | null = null;
  public saleCnt: number | null = null;
  public saleAmt: number | null = null;
  public returnCnt: number | null = null;
  public returnAmt: number | null = null;
  public totalGratuityAmt: number | null = null;
  public batchTransactions?: any;
  public transactionDetails?: any;
}
