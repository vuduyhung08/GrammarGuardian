/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ViewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionVM {
    private int Id;
    private int UserId;
    private int PackageId;
    private int LimitText;
    private int CheckTime;
    private String Description;
    private String Title;
    private float Price;
    private int PackageLimitText;
    private int PakcageCheckTime;
}
