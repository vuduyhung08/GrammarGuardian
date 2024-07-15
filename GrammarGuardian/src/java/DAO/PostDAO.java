/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.Post;
import Model.Post_Error;
import Model.Post_Update;
import Model.ViewModel.CommentViewModel;
import Model.ViewModel.ViewPostDetailAdmin;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class PostDAO extends DBContext {

    private Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public PostDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getAllPostSpendingTotal() {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Status = 1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // get all post admin and total 
    public int getAllPostAdminTotal() {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT COUNT(*) FROM [Post]";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<ViewPostDetailAdmin> getAllPostInAdmin(int index) {
        List<ViewPostDetailAdmin> listPostsView = new ArrayList<>();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT p.PostId, p.Title, p.Description, p.Status, p.CreateAt, u.UserName\n"
                    + "FROM Post p\n"
                    + "JOIN [User] u ON u.UserId = p.UserId\n"
                    + "WHERE p.Status != 2\n"
                    + "ORDER BY p.PostId DESC\n"
                    + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 8);

            rs = ps.executeQuery();
            while (rs.next()) {
                ViewPostDetailAdmin viewPost = new ViewPostDetailAdmin();
                viewPost.setPostId(rs.getInt("PostId"));
                viewPost.setTitle(rs.getString("Title"));
                viewPost.setDescription(rs.getString("Description"));
                viewPost.setStatus(rs.getInt("Status"));
                viewPost.setCreateAt(rs.getDate("CreateAt").toString());
                viewPost.setUserName(rs.getString("UserName"));
                listPostsView.add(viewPost);
            }
            return listPostsView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPostsView;
    }
    
    // search title and type = all
     public int searchPostManagePageTitleTotal(String title) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Title LIKE ? and Status != 2";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
     public List<ViewPostDetailAdmin> searchPostManagePageByTitle(String title, int index) {
        List<ViewPostDetailAdmin> listPostsSearch = new ArrayList<>();
        try {
            String sql = "SELECT p.PostId, p.Title, p.Description, p.Status, p.CreateAt, u.UserName\n"
                    + "FROM Post p\n"
                    + "JOIN [User] u ON u.UserId = p.UserId\n"
                    + "WHERE p.Title like ? and Status != 2\n"
                    + "ORDER BY p.PostId DESC\n"
                    + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, (index - 1) * 8);
            rs = ps.executeQuery();
            while (rs.next()) {
                ViewPostDetailAdmin viewPost = new ViewPostDetailAdmin();
                viewPost.setPostId(rs.getInt("PostId"));
                viewPost.setTitle(rs.getString("Title"));
                viewPost.setDescription(rs.getString("Description"));
                viewPost.setStatus(rs.getInt("Status"));
                viewPost.setCreateAt(rs.getDate("CreateAt").toString());
                viewPost.setUserName(rs.getString("UserName"));
                listPostsSearch.add(viewPost);
            }
            return listPostsSearch;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPostsSearch;
    }
     
    // getTotal Post Type Search  + Get list post search
    public int getPostTotalByTypeSearch(int type) {
        try {
            String sql = "select count(*) from Post where Status = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, type);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ViewPostDetailAdmin> getPostByTypeSearch(int type, int index) {
        List<ViewPostDetailAdmin> listPostView = new ArrayList<>();
        try {
            String sql = "SELECT p.PostId, p.Title, p.Description, p.Status, p.CreateAt, u.UserName\n"
                    + "FROM Post p\n"
                    + "JOIN [User] u ON u.UserId = p.UserId\n"
                    + "WHERE p.Status = ?\n"
                    + "ORDER BY p.PostId DESC\n"
                    + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, type);
            ps.setInt(2, (index - 1) * 8);
            rs = ps.executeQuery();
            while (rs.next()) {
                ViewPostDetailAdmin viewPost = new ViewPostDetailAdmin();
                viewPost.setPostId(rs.getInt("PostId"));
                viewPost.setTitle(rs.getString("Title"));
                viewPost.setDescription(rs.getString("Description"));
                viewPost.setStatus(rs.getInt("Status"));
                viewPost.setUserName(rs.getString("UserName"));
                viewPost.setCreateAt(rs.getDate("CreateAt").toString());
                listPostView.add(viewPost);
            }
            return listPostView;
        } catch (Exception e) {
        }
        return listPostView;
    }
     
     // get total post by type and title + all post search title and 
    public int getPostTotalByTitleAndType(String title, int type) {
        try {
            String sql = "select count(*) from Post where Title like ? and Status = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ViewPostDetailAdmin> getPostsByTitleAndType(String title, int type, int index) {
        List<ViewPostDetailAdmin> listViewPost = new ArrayList<>();
        try {
            String sql = "SELECT p.PostId, p.Title, p.Description, p.Status, p.CreateAt, u.UserName\n"
                    + "FROM Post p\n"
                    + "JOIN [User] u ON u.UserId = p.UserId\n"
                    + "WHERE p.Status = ? and p.Title like ?\n"
                    + "ORDER BY p.PostId DESC\n"
                    + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, type);
            ps.setString(2, "%" + title + "%");
            ps.setInt(3, (index - 1) * 8);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ViewPostDetailAdmin listPost = new ViewPostDetailAdmin();
                listPost.setPostId(rs.getInt("PostId"));
                listPost.setTitle(rs.getString("Title"));
                listPost.setDescription(rs.getString("Description"));
                listPost.setStatus(rs.getInt("Status"));
                listPost.setUserName(rs.getString("UserName"));
                listPost.setCreateAt(rs.getDate("CreateAt").toString());
                listViewPost.add(listPost);
            }
            return listViewPost;
        } catch (Exception e) {
        }
        return listViewPost;
    }
     
    // list user manager approve
    public List<Post> getAllPostSpending(int index) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT * FROM [Post] WHERE Status = 1 ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 8);

            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getDate("CreateAt").toString());
                post.setUserId(rs.getInt("UserId"));
                String base64Image = null;
                byte[] imgData = rs.getBytes("Image");
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }
                post.setImage(base64Image);
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public int searchPostHomePageByTitleTotal(String title) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Title LIKE ? AND Status = 0";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Post> searchPostHomePageByTitle(String title, int index) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT * FROM [Post] WHERE Title LIKE ? AND Status = 0 ORDER BY PostId DESC OFFSET ? ROW FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getString("CreateAt"));
                String base64Image = null;
                byte[] imgData = rs.getBytes("Image");
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }
                post.setImage(base64Image);
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

   

    public Post getPostById(int postId) {
        try {
            String sql = "SELECT * FROM [Post] WHERE PostId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getString("CreateAt"));
                int postUpdatedId = rs.getInt("UpdatePostId");
                if (postUpdatedId > 0) {
                    post.setUpdatePostId(postUpdatedId);
                }

                String base64Image = null;
                byte[] imgData = rs.getBytes("Image");
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }
                post.setImage(base64Image);
                return post;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Comment
    public boolean commentPost(int postId, String Content, int UserId) {
        try {
            String sql = "INSERT INTO [Comment] (PostId, Content, UserId, CreateAt)"
                    + " VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.setString(2, Content);
            ps.setInt(3, UserId);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(4, currentDate);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // get lisst comment
    public int getListUserCommentInPostTotal(int postId) {
        try {
            String sql = "SELECT COUNT(*) FROM [Comment] WHERE PostId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // get lisst comment
    public List<CommentViewModel> getListUserCommentInPost(int postId, int index) {
        try {
            int userId = 0;
            List<CommentViewModel> listComment = new ArrayList<>();
            String sql = "SELECT * FROM [Comment] WHERE PostId = ? ORDER BY Id DESC OFFSET ? ROW FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                CommentViewModel commentVM = new CommentViewModel();
                commentVM.setId(rs.getInt("Id"));
                commentVM.setPostId(rs.getInt("PostId"));
                commentVM.setContent(rs.getString("Content"));
                commentVM.setUserId(rs.getInt("UserId"));
                commentVM.setCreateAt(rs.getString("CreateAt"));
                // luu cai userId cua nguoi comment
                userId = rs.getInt("UserId");
                sql = "SELECT * FROM [User] WHERE UserId = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ResultSet rs1 = ps.executeQuery();
                while (rs1.next()) {
                    String firstName = rs1.getString("FirstName");
                    String lastName = rs1.getString("LastName");
                    String fullName = firstName + " " + lastName;
                    commentVM.setUserName(fullName);
                    String base64Image = null;
                    byte[] imgData = rs1.getBytes("Image");
                    if (imgData != null) {
                        base64Image = Base64.getEncoder().encodeToString(imgData);
                    }
                    commentVM.setAvatar(base64Image);
                }
                listComment.add(commentVM);
            }
            return listComment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean ConfirmPostByPostId(int postId) {
        try {
            String sql = "UPDATE POST SET Status = 3 WHERE PostId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean RejectPostByPostId(int postId) {
        try {
            String sql = "UPDATE POST SET Status = 2 WHERE PostId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Post_Error> getErrorsByPostId(int postId) {
        List<Post_Error> errors = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Post_Error WHERE PostId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post_Error error = new Post_Error();
                error.setId(rs.getInt("Id"));
                error.setPostId(rs.getInt("PostId"));
                error.setErrorText(rs.getString("ErrorText"));
                error.setExplain(rs.getString("Explain"));
                error.setSuggestion(rs.getString("Suggestion"));
                error.setStart_Position(rs.getInt("Start_Position"));
                error.setEnd_Position(rs.getInt("End_Position"));
                errors.add(error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errors;
    }

    public boolean updatePostErrors(int postId, String[] errorIds, String[] errorTexts, String[] suggestions) {
        try {
            for (int i = 0; i < errorIds.length; i++) {
                String sql = "UPDATE Post_Error SET ErrorText = ?, Suggestion = ? WHERE Id = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, errorTexts[i]);
                ps.setString(2, suggestions[i]);
                ps.setInt(3, Integer.parseInt(errorIds[i]));
                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePostDetail(Post post, Part image) {
        try {
            if (image != null) {
                String sql = "UPDATE Post SET Title = ?, UpdateAt = ?, Image = ?  WHERE PostId = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, post.getTitle());
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String currentDate = dateFormat.format(date);
                ps.setString(2, currentDate);
                InputStream fileContent = image.getInputStream();
                ps.setBinaryStream(3, fileContent, (int) image.getSize());
                ps.setInt(4, post.getPostId());

            } else {
                String sql = "UPDATE Post SET Title = ?, UpdateAt = ?  WHERE PostId = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, post.getTitle());
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String currentDate = dateFormat.format(date);
                ps.setString(2, currentDate);
                ps.setInt(3, post.getPostId());
            }
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int UpdatePostWithSuggestion(Post post) {
        try {
            int updatedPostId = 0;
            // insert vao bang post_update va update Post with PostUpdate_Id la cai Id cua thang nay
            String sql = "INSERT INTO [Post_Update] (Description, Title)"
                    + " VALUES (?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, post.getDescription());
            ps.setString(2, post.getTitle());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                sql = "SELECT * FROM Post_Update ORDER BY Id DESC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    updatedPostId = rs.getInt("Id");
                    System.out.println("PostUpdate Id  " + updatedPostId);
                    sql = "UPDATE [Post] SET UpdatePostId = ? WHERE PostId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, updatedPostId);
                    ps.setInt(2, post.getPostId());
                    affectedRow = ps.executeUpdate();
                    if (affectedRow > 0) {
                        return updatedPostId;
                    }
                }
            }
            return updatedPostId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Post_Update getPostUpdateById(int id) {
        try {
            Post_Update post_Update = new Post_Update();
            String sql = "SELECT * FROM Post_Update WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                post_Update.setId(rs.getInt("Id"));
                post_Update.setDescription(rs.getString("Description"));
                post_Update.setTitle(rs.getString("Title"));
            }
            return post_Update;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPostFilterTotal(int userId, int status) {
        List<Post> listPosts = new ArrayList();
        try {
            if (status == 7) {
                String sql = "SELECT COUNT(*) FROM [Post] p WHERE p.UserId = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
            } else if (status == 5) {
                String sql = "SELECT COUNT(*) FROM [Post] p JOIN [Post_Favourite] pf ON p.PostId = pf.PostId AND pf.UserId = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
            } else {
                String sql = "SELECT COUNT(*) FROM [Post] p "
                        + "WHERE p.UserId = ? AND p.Status = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, status);

            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Post> getPostFilter(int index, int userId, int status) {
        List<Post> listPosts = new ArrayList();
        try {
            if (status == 7) {
                String sql = "SELECT p.UserId, p.PostId, p.Title, p.Description, p.CreateAt, p.Status, p.Image FROM [Post] p WHERE p.UserId = ?"
                        + " ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, (index - 1) * 8);
            } else if (status == 5) {
                String sql = "SELECT p.UserId, p.PostId, p.Title, p.Description, p.CreateAt, p.Status, p.Image FROM [Post] p JOIN [Post_Favourite] pf ON p.PostId = pf.PostId AND pf.UserId = ? "
                        + " ORDER BY p.PostId DESC OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, (index - 1) * 8);
            } else {
                String sql = "SELECT p.UserId, p.PostId, p.Title, p.Description, p.CreateAt, p.Status, p.Image FROM [Post] p "
                        + "WHERE p.UserId = ? AND p.Status = ? "
                        + " ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, status);
                ps.setInt(3, (index - 1) * 8);

            }

            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getDate("CreateAt").toString());
                post.setUserId(rs.getInt("UserId"));
                String base64Image = null;
                byte[] imgData = rs.getBytes("Image");
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }
                post.setImage(base64Image);
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public int getPostFilterSearchTotal(String search, int userId, int status) {
        List<Post> listPosts = new ArrayList();
        try {
            if (status == 7) {
                String sql = "SELECT COUNT(*) FROM [Post] p WHERE p.UserId = ? AND p.Title LIKE ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setString(2, "%" + search + "%");
            } else if (status == 5) {
                String sql = "SELECT COUNT(*) FROM [Post] p JOIN [Post_Favourite] pf ON p.PostId = pf.PostId AND pf.UserId = ? "
                        + "AND p.Title LIKE ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setString(2, "%" + search + "%");
            } else {
                String sql = "SELECT COUNT(*) FROM [Post] p "
                        + "WHERE p.UserId = ? AND p.Status = ? AND p.Title LIKE ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, status);
                ps.setString(3, "%" + search + "%");
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Post> getPostFilterSearch(int index, String search, int userId, int status) {
        List<Post> listPosts = new ArrayList();
        try {
            if (status == 7) {
                String sql = "SELECT p.UserId, p.PostId, p.Title, p.Description, p.CreateAt, p.Status, p.Image FROM [Post] p WHERE p.UserId = ? AND p.Title LIKE ? "
                        + " ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setString(2, "%" + search + "%");

                ps.setInt(3, (index - 1) * 8);
            } else if (status == 5) {
                String sql = "SELECT p.UserId, p.PostId, p.Title, p.Description, p.CreateAt, p.Status, p.Image FROM [Post] p JOIN [Post_Favourite] pf ON p.PostId = pf.PostId AND pf.UserId = ? "
                        + "AND p.Title LIKE ? "
                        + " ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setString(2, "%" + search + "%");    
                ps.setInt(3, (index - 1) * 8);

            } else {
                String sql = "SELECT p.UserId, p.PostId, p.Title, p.Description, p.CreateAt, p.Status, p.Image FROM [Post] p "
                        + "WHERE p.UserId = ? AND p.Status = ? AND p.Title LIKE ? "
                        + " ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, status);
                ps.setString(3, "%" + search + "%");
                ps.setInt(4, (index - 1) * 8);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getDate("CreateAt").toString());
                post.setUserId(rs.getInt("UserId"));
                String base64Image = null;
                byte[] imgData = rs.getBytes("Image");
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }
                post.setImage(base64Image);
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    // truongwf hop restore
    // neu post co updatepostId thi se query join 2 bang va lay content cua Postupdate
    // Nguoc lai khong co thi se lay 
    // Truong hop resotre se clear data cua bang poist
    public static void main(String[] args) {
        PostDAO postDAO = new PostDAO();
        List<Post_Error> list = postDAO.getErrorsByPostId(1034);
        for (Post_Error commentViewModel : list) {
            System.out.println(commentViewModel.toString());
        }
    }

}
