export interface IAuthable {
  authorize(amount?: number | string | null, isEstimated?: boolean): any;
}
