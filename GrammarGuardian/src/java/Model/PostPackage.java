/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostPackage {
    private int Id;
    private String Description;
    private String Title;
    private float Price;
    private int LimitText;
    private int CheckTime;
    private boolean Status;
    private String CreateAt;
    private String UpdateAt;
}
