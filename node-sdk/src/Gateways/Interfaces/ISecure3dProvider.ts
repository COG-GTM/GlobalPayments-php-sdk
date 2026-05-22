import { Secure3dVersion } from "../../Entities/Enums/Secure3dVersion";

export interface ISecure3dProvider {
  version: Secure3dVersion;
  processSecure3d(builder: any): Promise<any>;
}
