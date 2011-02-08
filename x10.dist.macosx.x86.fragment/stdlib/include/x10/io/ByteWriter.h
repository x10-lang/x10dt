#ifndef __X10_IO_BYTEWRITER_H
#define __X10_IO_BYTEWRITER_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_GLOBALREF_STRUCT_H_NODEPS
#include <x10/lang/GlobalRef.struct_h>
#undef X10_LANG_GLOBALREF_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace util { 
template<class FMGL(Element), class FMGL(Collection)> class Builder;
} } 
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace compiler { 
class Global;
} } 
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
class UnsupportedOperationException;
} } 
namespace x10 { namespace compiler { 
class Incomplete;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace io { 

template<class FMGL(T)> class ByteWriter;
template <> class ByteWriter<void>;
template<class FMGL(T)> class ByteWriter : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > FMGL(root);
    
    x10aux::ref<x10::util::Builder<x10_byte, FMGL(T)> > FMGL(b);
    
    void _constructor(x10aux::ref<x10::util::Builder<x10_byte, FMGL(T)> > b);
    
    static x10aux::ref<x10::io::ByteWriter<FMGL(T)> > _make(x10aux::ref<x10::util::Builder<x10_byte, FMGL(T)> > b);
    
    virtual void write(x10_byte x);
    virtual x10_long size();
    virtual x10aux::ref<x10::lang::String> toString();
    virtual FMGL(T) result();
    virtual void flush();
    virtual void close();
    virtual x10aux::ref<x10::io::ByteWriter<FMGL(T)> > x10__io__ByteWriter____x10__io__ByteWriter__this(
      );
    void __fieldInitializers1838();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class ByteWriter<void> : public x10::lang::Object {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_IO_BYTEWRITER_H

namespace x10 { namespace io { 
template<class FMGL(T)> class ByteWriter;
} } 

#ifndef X10_IO_BYTEWRITER_H_NODEPS
#define X10_IO_BYTEWRITER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/GlobalRef.h>
#include <x10/util/Builder.h>
#include <x10/lang/Byte.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/ClassCastException.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/compiler/Global.h>
#include <x10/lang/Long.h>
#include <x10/lang/UnsupportedOperationException.h>
#include <x10/compiler/Incomplete.h>
#include <x10/lang/String.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/Fun_0_1.h>
#ifndef X10_IO_BYTEWRITER__CLOSURE__0_CLOSURE
#define X10_IO_BYTEWRITER__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_io_ByteWriter__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_io_ByteWriter__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::io::ByteWriter<FMGL(T)> > me = ((__extension__ ({
            x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > __desugarer__var__58__ =
              saved_this->
                FMGL(root);
            
            //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10LocalDecl_c
            x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > __var773__;
            
            //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Labeled_c
            goto __ret2302; __ret2302: 
            //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Do_c
            do
            {
            {
                
                //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10If_c
                if (!((x10aux::struct_equals(x10::lang::Place_methods::place((__desugarer__var__58__)->location),
                                             x10::lang::Place_methods::_make(x10aux::here)))))
                {
                    
                    //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.lang.GlobalRef[x10.io.ByteWriter[T]]{self.home==here}"))));
                }
                
                //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
                __var773__ =
                  __desugarer__var__58__;
                
                //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Branch_c
                goto __ret2302_end_;
            }
            goto __ret2302_next_; __ret2302_next_: ;
            }
            while (false);
            goto __ret2302_end_; __ret2302_end_: ;
            __var773__;
        }))
        )->__apply();
        
        //#line 33 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
        x10::util::Builder<x10_byte, FMGL(T)>::add(x10aux::nullCheck(x10aux::nullCheck(me)->
                                                                       FMGL(b)), 
          x);
    }
    
    // captured environment
    x10aux::ref<x10::io::ByteWriter<FMGL(T)> > saved_this;
    x10_byte x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_io_ByteWriter__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_io_ByteWriter__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_io_ByteWriter__closure__0<FMGL(T) > >(storage));
        x10aux::ref<x10::io::ByteWriter<FMGL(T)> > that_saved_this = buf.read<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > >();
        x10_byte that_x = buf.read<x10_byte>();
        x10aux::ref<x10_io_ByteWriter__closure__0<FMGL(T) > > this_ = new (storage) x10_io_ByteWriter__closure__0<FMGL(T) >(that_saved_this, that_x);
        return this_;
    }
    
    x10_io_ByteWriter__closure__0(x10aux::ref<x10::io::ByteWriter<FMGL(T)> > saved_this, x10_byte x) : saved_this(saved_this), x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10:31-34";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_io_ByteWriter__closure__0<FMGL(T) > >x10_io_ByteWriter__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_io_ByteWriter__closure__0<FMGL(T) >::__apply, &x10_io_ByteWriter__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_io_ByteWriter__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_io_ByteWriter__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_io_ByteWriter__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_io_ByteWriter__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_IO_BYTEWRITER__CLOSURE__0_CLOSURE
#ifndef X10_IO_BYTEWRITER__CLOSURE__1_CLOSURE
#define X10_IO_BYTEWRITER__CLOSURE__1_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_io_ByteWriter__closure__1 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<FMGL(T)>::template itable <x10_io_ByteWriter__closure__1<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    FMGL(T) __apply() {
        
        //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::io::ByteWriter<FMGL(T)> > me = ((__extension__ ({
            x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > __desugarer__var__61__ =
              saved_this->
                FMGL(root);
            
            //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10LocalDecl_c
            x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > __var779__;
            
            //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Labeled_c
            goto __ret2305; __ret2305: 
            //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Do_c
            do
            {
            {
                
                //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10If_c
                if (!((x10aux::struct_equals(x10::lang::Place_methods::place((__desugarer__var__61__)->location),
                                             x10::lang::Place_methods::_make(x10aux::here)))))
                {
                    
                    //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.lang.GlobalRef[x10.io.ByteWriter[T]]{self.home==here}"))));
                }
                
                //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
                __var779__ =
                  __desugarer__var__61__;
                
                //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Branch_c
                goto __ret2305_end_;
            }
            goto __ret2305_next_; __ret2305_next_: ;
            }
            while (false);
            goto __ret2305_end_; __ret2305_end_: ;
            __var779__;
        }))
        )->__apply();
        
        //#line 53 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Return_c
        return x10::util::Builder<x10_byte, FMGL(T)>::result(x10aux::nullCheck(x10aux::nullCheck(me)->
                                                                                 FMGL(b)));
        
    }
    
    // captured environment
    x10aux::ref<x10::io::ByteWriter<FMGL(T)> > saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_io_ByteWriter__closure__1<FMGL(T) >* storage = x10aux::alloc<x10_io_ByteWriter__closure__1<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_io_ByteWriter__closure__1<FMGL(T) > >(storage));
        x10aux::ref<x10::io::ByteWriter<FMGL(T)> > that_saved_this = buf.read<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > >();
        x10aux::ref<x10_io_ByteWriter__closure__1<FMGL(T) > > this_ = new (storage) x10_io_ByteWriter__closure__1<FMGL(T) >(that_saved_this);
        return this_;
    }
    
    x10_io_ByteWriter__closure__1(x10aux::ref<x10::io::ByteWriter<FMGL(T)> > saved_this) : saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10:51-54";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<FMGL(T)>::template itable <x10_io_ByteWriter__closure__1<FMGL(T) > >x10_io_ByteWriter__closure__1<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_io_ByteWriter__closure__1<FMGL(T) >::__apply, &x10_io_ByteWriter__closure__1<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_io_ByteWriter__closure__1<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >, &x10_io_ByteWriter__closure__1<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_io_ByteWriter__closure__1<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_io_ByteWriter__closure__1<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_IO_BYTEWRITER__CLOSURE__1_CLOSURE
#ifndef X10_IO_BYTEWRITER_H_GENERICS
#define X10_IO_BYTEWRITER_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::io::ByteWriter<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::ByteWriter<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::io::ByteWriter<FMGL(T)> >(), 0, sizeof(x10::io::ByteWriter<FMGL(T)>))) x10::io::ByteWriter<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_BYTEWRITER_H_GENERICS
#ifndef X10_IO_BYTEWRITER_H_IMPLEMENTATION
#define X10_IO_BYTEWRITER_H_IMPLEMENTATION
#include <x10/io/ByteWriter.h>


template<class FMGL(T)> void x10::io::ByteWriter<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::io::ByteWriter<FMGL(T)>");
    
}


//#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10FieldDecl_c

//#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10FieldDecl_c

//#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::io::ByteWriter<FMGL(T)>::_constructor(x10aux::ref<x10::util::Builder<x10_byte, FMGL(T)> > b)
{
    this->::x10::lang::Object::_constructor();
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->x10::io::ByteWriter<FMGL(T)>::__fieldInitializers1838();
    
    //#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
      FMGL(b) =
      b;
    
}
template<class FMGL(T)>
x10aux::ref<x10::io::ByteWriter<FMGL(T)> > x10::io::ByteWriter<FMGL(T)>::_make(
  x10aux::ref<x10::util::Builder<x10_byte, FMGL(T)> > b)
{
    x10aux::ref<x10::io::ByteWriter<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::io::ByteWriter<FMGL(T)> >(), 0, sizeof(x10::io::ByteWriter<FMGL(T)>))) x10::io::ByteWriter<FMGL(T)>();
    this_->_constructor(b);
    return this_;
}



//#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::io::ByteWriter<FMGL(T)>::write(
  x10_byte x) {
    
    //#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(x10::lang::Place_methods::_make(x10aux::here),
                               x10::lang::Place_methods::place((((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
                                                                  FMGL(root))->location))))
    {
        
        //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::io::ByteWriter<FMGL(T)> > me =
          ((__extension__ ({
            x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > __desugarer__var__57__ =
              ((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
                FMGL(root);
            
            //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10LocalDecl_c
            x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > __var772__;
            
            //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Labeled_c
            goto __ret2301; __ret2301: 
            //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Do_c
            do
            {
            {
                
                //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10If_c
                if (!((x10aux::struct_equals(x10::lang::Place_methods::place((__desugarer__var__57__)->location),
                                             x10::lang::Place_methods::_make(x10aux::here)))))
                {
                    
                    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.lang.GlobalRef[x10.io.ByteWriter[T]]{self.home==here}"))));
                }
                
                //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
                __var772__ =
                  __desugarer__var__57__;
                
                //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Branch_c
                goto __ret2301_end_;
            }
            goto __ret2301_next_; __ret2301_next_: ;
            }
            while (false);
            goto __ret2301_end_; __ret2301_end_: ;
            __var772__;
        }))
        )->__apply();
        
        //#line 28 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
        x10::util::Builder<x10_byte, FMGL(T)>::add(x10aux::nullCheck(x10aux::nullCheck(me)->
                                                                       FMGL(b)), 
          x);
        
        //#line 29 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Return_c
        return;
    }
    
    //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
    x10::lang::Runtime::runAt(x10::lang::Place_methods::place((((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
                                                                 FMGL(root))->location),
                              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_io_ByteWriter__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_io_ByteWriter__closure__0<FMGL(T)>)))x10_io_ByteWriter__closure__0<FMGL(T)>(this, x)))));
}

//#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_long x10::io::ByteWriter<FMGL(T)>::size(
  ) {
    
    //#line 37 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Throw_c
    x10aux::throwException(x10aux::nullCheck(x10::lang::UnsupportedOperationException::_make()));
}

//#line 39 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::String>
  x10::io::ByteWriter<FMGL(T)>::toString(
  ) {
    
    //#line 40 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(x10::lang::Place_methods::_make(x10aux::here),
                               x10::lang::Place_methods::place((((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
                                                                  FMGL(root))->location))))
    {
        
        //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::io::ByteWriter<FMGL(T)> > me =
          ((__extension__ ({
            x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > __desugarer__var__59__ =
              ((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
                FMGL(root);
            
            //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10LocalDecl_c
            x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > __var776__;
            
            //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Labeled_c
            goto __ret2303; __ret2303: 
            //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Do_c
            do
            {
            {
                
                //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10If_c
                if (!((x10aux::struct_equals(x10::lang::Place_methods::place((__desugarer__var__59__)->location),
                                             x10::lang::Place_methods::_make(x10aux::here)))))
                {
                    
                    //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.lang.GlobalRef[x10.io.ByteWriter[T]]{self.home==here}"))));
                }
                
                //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
                __var776__ =
                  __desugarer__var__59__;
                
                //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Branch_c
                goto __ret2303_end_;
            }
            goto __ret2303_next_; __ret2303_next_: ;
            }
            while (false);
            goto __ret2303_end_; __ret2303_end_: ;
            __var776__;
        }))
        )->__apply();
        
        //#line 42 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(me)->toString();
        
    }
    
    //#line 44 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
              FMGL(root))->toString();
    
}

//#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::io::ByteWriter<FMGL(T)>::result(
  ) {
    
    //#line 47 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(x10::lang::Place_methods::_make(x10aux::here),
                               x10::lang::Place_methods::place((((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
                                                                  FMGL(root))->location))))
    {
        
        //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::io::ByteWriter<FMGL(T)> > me =
          ((__extension__ ({
            x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > __desugarer__var__60__ =
              ((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
                FMGL(root);
            
            //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10LocalDecl_c
            x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > __var778__;
            
            //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Labeled_c
            goto __ret2304; __ret2304: 
            //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Do_c
            do
            {
            {
                
                //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10If_c
                if (!((x10aux::struct_equals(x10::lang::Place_methods::place((__desugarer__var__60__)->location),
                                             x10::lang::Place_methods::_make(x10aux::here)))))
                {
                    
                    //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.lang.GlobalRef[x10.io.ByteWriter[T]]{self.home==here}"))));
                }
                
                //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
                __var778__ =
                  __desugarer__var__60__;
                
                //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Branch_c
                goto __ret2304_end_;
            }
            goto __ret2304_next_; __ret2304_next_: ;
            }
            while (false);
            goto __ret2304_end_; __ret2304_end_: ;
            __var778__;
        }))
        )->__apply();
        
        //#line 49 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Return_c
        return x10::util::Builder<x10_byte, FMGL(T)>::result(x10aux::nullCheck(x10aux::nullCheck(me)->
                                                                                 FMGL(b)));
        
    }
    
    //#line 51 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Return_c
    return x10::lang::Runtime::template evalAt<FMGL(T) >(
             x10::lang::Place_methods::place((((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
                                                FMGL(root))->location),
             x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > >(x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> >(x10aux::ref<x10_io_ByteWriter__closure__1<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<FMGL(T)> >(sizeof(x10_io_ByteWriter__closure__1<FMGL(T)>)))x10_io_ByteWriter__closure__1<FMGL(T)>(this)))));
    
}

//#line 56 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::io::ByteWriter<FMGL(T)>::flush(
  ) {
 
}

//#line 57 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::io::ByteWriter<FMGL(T)>::close(
  ) {
 
}

//#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::io::ByteWriter<FMGL(T)> >
  x10::io::ByteWriter<FMGL(T)>::x10__io__ByteWriter____x10__io__ByteWriter__this(
  ) {
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this);
    
}

//#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::io::ByteWriter<FMGL(T)>::__fieldInitializers1838(
  ) {
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/ByteWriter.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this)->
      FMGL(root) = x10::lang::GlobalRef_methods<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > >::_make(((x10aux::ref<x10::io::ByteWriter<FMGL(T)> >)this));
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::io::ByteWriter<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::io::ByteWriter<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::io::ByteWriter<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(root));
    
}

template<class FMGL(T)> void x10::io::ByteWriter<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(root) = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::io::ByteWriter<FMGL(T)> > > >();
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::io::ByteWriter<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::io::ByteWriter<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::io::ByteWriter<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.io.ByteWriter";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 1, parents, 1, params, variances);
}
#endif // X10_IO_BYTEWRITER_H_IMPLEMENTATION
#endif // __X10_IO_BYTEWRITER_H_NODEPS
