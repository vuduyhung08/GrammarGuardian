/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    private int PostId;
    private String Title;
    private String Description;
    private int UserId;
    private int Status;
    private String CreateAt;
    private String UpdateAt;
    private String DeleteAt;
    private int UpdatePostId;
    private String Image;
}
