export interface IRefundable {
  refund(amount?: number | string | null): any;
}
