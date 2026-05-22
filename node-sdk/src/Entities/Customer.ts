export class Customer extends RecurringEntity {
  public title?: any;
  public firstName?: any;
  public lastName?: any;
  public middleName?: any;
  public company?: any;
  public businessName?: any;
  public customerPassword?: any;
  public dateOfBirth?: any;
  public domainName?: any;
  public deviceFingerPrint?: any;
  public address?: any;
  public homePhone?: any;
  public workPhone?: any;
  public fax?: any;
  public mobilePhone?: any;
  public email?: any;
  public comments?: any;
  public department?: any;
  public status?: any;
  public phone?: any;
  public documents: any = [];
  public paymentMethods: any[] = [];
}
